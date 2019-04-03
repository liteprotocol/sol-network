package org.tron.gateway;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.tron.gateway.task.ChainTask;

;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
@Slf4j(topic = "app")
public class App {

  private static String mainGatewayAccount = "TE5xHVk2KZZTKz81MUeh89pZzBPbFz91yN";
  private static String mainGatewayAddress = "127.0.0.1:9092";

  private static String sideChainGatewayAccount = "TE5xHVk2KZZTKz81MUeh89pZzBPbFz91yN";
  private static String sideChainGatewayAddress = "127.0.0.1:9092";

  private static int fixedThreads = 5;

  private static ExecutorService executor = Executors.newFixedThreadPool(fixedThreads);

  public static void main(String[] args) {
    logger.info("start...");
    ChainTask mainChainTask = new ChainTask(mainGatewayAccount, mainGatewayAddress, fixedThreads);
    ChainTask sideChainTask = new ChainTask(sideChainGatewayAccount, sideChainGatewayAddress,
        fixedThreads);
    mainChainTask.start();
    sideChainTask.start();
    logger.info("end...");
  }
}
