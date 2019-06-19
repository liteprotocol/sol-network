package org.tron.service.eventactuator.mainchain;

import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Objects;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.tron.client.SideChainGatewayApi;
import org.tron.common.exception.RpcConnectException;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.WalletUtil;
import org.tron.protos.Protocol.Transaction;
import org.tron.protos.Sidechain.DepositTRC721Event;
import org.tron.protos.Sidechain.EventMsg;
import org.tron.protos.Sidechain.EventMsg.EventType;
import org.tron.protos.Sidechain.TaskEnum;
import org.tron.service.check.TransactionExtensionCapsule;
import org.tron.service.eventactuator.Actuator;

@Slf4j(topic = "mainChainTask")
public class DepositTRC721Actuator extends Actuator {

  private static final String NONCE_TAG = "deposit_";

  private DepositTRC721Event event;
  @Getter
  private EventType type = EventType.DEPOSIT_TRC721_EVENT;

  public DepositTRC721Actuator(String from, String contractAddress, String uid,
      String nonce) {
    ByteString fromBS = ByteString.copyFrom(WalletUtil.decodeFromBase58Check(from));
    ByteString uidBS = ByteString.copyFrom(ByteArray.fromString(uid));
    ByteString contractAddressBS = ByteString
        .copyFrom(WalletUtil.decodeFromBase58Check(contractAddress));
    ByteString nonceBS = ByteString.copyFrom(ByteArray.fromString(nonce));
    this.event = DepositTRC721Event.newBuilder().setFrom(fromBS).setUId(uidBS)
        .setContractAddress(contractAddressBS).setNonce(nonceBS).build();
  }

  public DepositTRC721Actuator(EventMsg eventMsg) throws InvalidProtocolBufferException {
    this.event = eventMsg.getParameter().unpack(DepositTRC721Event.class);
  }

  @Override
  public TransactionExtensionCapsule createTransactionExtensionCapsule()
      throws RpcConnectException {
    if (Objects.nonNull(transactionExtensionCapsule)) {
      return transactionExtensionCapsule;
    }

    String fromStr = WalletUtil.encode58Check(event.getFrom().toByteArray());
    String uidStr = event.getUId().toStringUtf8();
    String contractAddressStr = WalletUtil.encode58Check(event.getContractAddress().toByteArray());
    String nonceStr = ByteArray.toHexString(event.getNonce().toByteArray());
    logger.info(
        "DepositTRC721Actuator, from: {}, tokenId: {}, contractAddress: {}, nonce: {}",
        fromStr, uidStr, contractAddressStr, nonceStr);
    Transaction tx = SideChainGatewayApi
        .mintToken721Transaction(fromStr, contractAddressStr, uidStr, nonceStr);
    this.transactionExtensionCapsule = new TransactionExtensionCapsule(TaskEnum.SIDE_CHAIN,
        NONCE_TAG + nonceStr, tx);
    return this.transactionExtensionCapsule;
  }

  @Override
  public EventMsg getMessage() {
    return EventMsg.newBuilder().setParameter(Any.pack(this.event)).setType(getType()).build();
  }

  @Override
  public byte[] getNonceKey() {
    return ByteArray.fromString(NONCE_TAG + event.getNonce().toStringUtf8());
  }

  @Override
  public byte[] getNonce() {
    return event.getNonce().toByteArray();
  }

}
