# SOLWEB

SolWeb inherits from LiteWeb and services for Sol-network. We  encapsulated two objects (mainchain and sidechain) based on LiteWeb. The methods and attributes in mainchain or sidechain are exactly the same as the liteweb instance. For example, users can use solweb.mainchain.xlt.getBalance() to get balance from the mainchain. Futhermore, we add some new methods which are as follows in SolWeb class so that users can use them to contact between the main chain and the side chain. 

## SolWeb Object

###### SubWeb Instantiation

To use the SolWeb library in your App, you need to instantiate Solweb. 

You can define two objects mainOptions and sideOptions which respectively contains the following key:

- fullNode
- solidityNode
- eventServer

You can also set a:

- fullHost

which works as a jolly. If you do so, though, the more precise specification has priority.

And besides, you may also need to set gateway address:

- mainGatewayAddress 
-  sideGatewayAddress
-  sideChainId
- privateKey (optional)

Supposing you are using a server which provides everything, like LiteGrid, you can instantiate SolWeb as:

```javascript
const solWeb = new SolWeb({
  fullHost: 'https://mainapi.litegrid.io'
}, {
  fullHost: 'https://sideapi.litegrid.io'
},
  mainGatewayAddress: 'TEEXEWrkMFKapSMJ6mErg39ELFKDqEs6w3' ,
  sideGatewayAddress: 'TXPHCzmAmjyERtWES6EXTYqUPfJfQSzp2m',
  sideChainId,
  privateKey: '...');
```

If you are using different servers for anything, you can instantiate like:

```javascript
const solWeb = new SolWeb({
  fullNode: 'http://fullnode.lite.network',
  solidityNode: 'http://solidity.lite.network',
  eventServer: 'http://mainapi.litegrid.io'
}, {
  fullNode: 'http://fullnode.sol.network',
  solidityNode: 'http://solidity.sol.network',
  eventServer: 'http://sideapi.litegrid.io'
},
  mainGatewayAddress: 'TTGhuSDKr561gzHFjkZ1V4ZtMgUEFLa7ct',
  sideGatewayAddress: 'TBAHKAbjZ6nn3B4cAfNd2ZXscRoskaxbk2',
  sideChainId,  
  privateKey: '...');
```

For example, you can create a solWeb instance connected to out sol network test-net like:

```javascript
const solWeb = new SolWeb({
  fullNode: 'http://39.107.123.182:8090',
  solidityNode: 'http://47.252.84.158:8090',
  eventServer: 'http://47.252.84.141:8080'
}, {
  fullNode: 'http://47.252.85.90:8090',
  solidityNode: 'http://47.252.85.90:8091',
  eventServer: 'http://47.252.85.90:8090'
},
  mainGatewayAddress: 'TGHxhFu4jV4XqMGmk3tEQdSeihWVHE9kBP',
  sideGatewayAddress: 'TBHr5KpbA7oACUysTKxHiAD7c6X6nkZii1',
  '410e7e8f7fbd5d5ffae4f2a7d2f97a83b93db7b5bc',  
  privateKey: '...');
```

## New functions in SolWeb

## Asset Deposit

deposit asset from mainchain to sidechain

#### depositXlt

###### deposit xlt  from main chain to side chain

```javascript
// Format
solWeb.depostiXlt(callValue, feeLimit, options);

// example
solWeb.depositXlt(100000000, 1000000);
```

###### Arguments

| Parameter | Description                             | Type                   | Options  |
| --------- | --------------------------------------- | ---------------------- | -------- |
| callValue | Amount of XLT (Units in SOL) to deposit | Integer (Units in SOL) | Required |
| feeLimit  | Cost limit                              | Integer, long          | Required |
| options   | The permissions Id                      | Object                 | Optional |

#### depositTrc10

###### deposit trc10 token from main chain to side chain

```javascript
// format
solWeb.depositTrc10(tokenId, tokenValue, feeLimit, options);

// example
solWeb.depositTrc10(100059, 10000000, 100000);
```

###### Arguments

| Parameter  | Description                                     | Type                   | Options  |
| ---------- | ----------------------------------------------- | ---------------------- | -------- |
| tokenId    | Token Id of trc10                               | Integer                | Required |
| tokenValue | Amount of trc10 token (Units in SOL) to deposit | Integer (Units in SOL) | Required |
| feeLimit   | Cost limit                                      | Integer, long          | Required |
| options    | The permissions Id                              | Object                 | Optional |

#### depositTrc20

###### deposit trc20 token from main chain to side chain

Note: You have to mapping TRC20 contract to side chain with the mappingTrc20 function provided by SolWeb. Then you also have to use  the approveTrc20 function. Only done with the two steps before, you can depositTrc20 from main chain to side chain.

```javascript
// format
solWeb.depositTrc10(num, feeLimit, contractAddress, options);

// example
solWeb.depositTrc10(1000000, 1000000, 'TD9Jrm546pGkzRu7K5nitMxk8nn75wXNkQ');
```

###### Arguments

| Parameter       | Description                                | Type          | Options  |
| --------------- | ------------------------------------------ | ------------- | -------- |
| Num             | Amount of TRC20  (Units in SOL) to deposit | Integer       | Required |
| feeLimit        | Cost limit                                 | Integer, long | Required |
| contractAddress | Main Chain TRC20 Contract Address          | String        | Required |
| options         | The permissions Id                         | Object        | Optional |

#### depositTrc721

###### deposit trc20 token from main chain to side chain

Note: You have to use mappingTrc721 and approveTrc721 before depositTrc721 like depositTrc20.

```javascript
// format
solWeb.depositTrc721(id, feeLimit, contractAddress, options);

// example
solWeb.depositTrc10(1000000, 1000000, 'TCLRqK6aP2xsCZWhE2smkYzdRHf9uvyz5P');
```

###### Arguments

| Parameter       | Description                        | Type          | Options  |
| --------------- | ---------------------------------- | ------------- | -------- |
| id              | Id of TRC721 to deposit            | Integer       | Required |
| feeLimit        | cost limit                         | Integer, long | Required |
| contractAddress | Main Chain TRC721 Contract Address | String        | Required |
| options         | The permissions Id                 | Object        | Optional |

## Asset Approve

###### Before depositTrc20 and depositTrc721, you should call the approve function.

### approveTrc20

###### Approve trc20 token 

```javascript
// format
solWeb.approveTrc20(num, feeLimit, contractAddress, options);

// example
solWeb.approveTrc20(10000, 10000000, 'TGKuXDnvdHv9RNE6BPXUtNLK2FrVMBDAuA');
```

###### Arguments

| Parameter       | Description                       | Type          | Options  |
| --------------- | --------------------------------- | ------------- | -------- |
| Num             | Num of TRC20                      | Integer       | Required |
| contractAddress | Main Chain TRC20 Contract Address | String        | Required |
| feeLimit        | Cost limit                        | Integer, long | Required |
| options         | The permissions Id                | Object        | Optional |

### approveTrc721

###### Approve trc20 token 

```javascript
// format
solWeb.approveTrc721(id, feeLimit, contractAddress, options);

// example
solWeb.approveTrc721(100, 10000000, 'TUxDmFbEceGgjWCb6rLVcrFgnsWwofPdPq');
```

###### Arguments

| Parameter       | Description                        | Type          | Options  |
| --------------- | ---------------------------------- | ------------- | -------- |
| Num             | Id of TRC721                       | Integer       | Required |
| contractAddress | Main Chain TRC721 Contract Address | String        | Required |
| feeLimit        | Cost limit                         | Integer, long | Required |
| options         | The permissions Id                 | Object        | Optional |

## Asset Mapping

### mappingTrc20

###### mapping trc20 token to side chain

```javascript
// format
mappingTrc20(xltHash, feeLimit, options);

// example
mappingTrc20('548442d9080605a60adf1d30cc126a2b9c6308cbe9ec224f8c67a6c2590fa299', 100000, , options);
```

###### Arguments

| Parameter | Description                                                  | Type          | Options  |
| --------- | ------------------------------------------------------------ | ------------- | -------- |
| xltHash   | The hash value of the transaction for the main chain deployment TRC20 contract | Hex string    | Required |
| feeLimit  | cost limit                                                   | Integer, long | Required |
| options   | The permissions Id                                           | Object        | Optional |

### mappingTrc721

###### mapping trc721 token to side chain

```javascript
// format
mappingTrc721(xltHash, feeLimit, options);

// example
mappingTrc721('548442d9080605a60adf1d30cc126a2b9c6308cbe9ec224f8c67a6c2590fa299', 100000, , options);
```

###### Arguments

| Parameter | Description                                                  | Type          | Options  |
| --------- | ------------------------------------------------------------ | ------------- | -------- |
| xltHash   | The hash value of the transaction for the main chain deployment TRC721 contract | Hex string    | Required |
| feeLimit  | cost limit                                                   | Integer, long | Required |
| options   | The permissions Id                                           | Object        | Optional |

## Asset Withdraw

#### withdrawXlt

###### Withdraw xlt  from side chain to main chain

```javascript
// Format
solWeb.withdrawXlt(callValue, feeLimit, options);

// example
solWeb.withdrawXlt(100000000, 1000000);
```

###### Arguments

| Parameter | Description                             | Type                   | Options  |
| --------- | --------------------------------------- | ---------------------- | -------- |
| callValue | Amount of XLT (Units in SOL) to deposit | Integer (Units in SOL) | Required |
| feeLimit  | Cost limit                              | Integer, long          | Required |
| options   | The permissions Id                      | Object                 | Optional |

### withdrawTrc10

###### Withdraw trc10 token from side chain to main chain

```javascript
// format
solWeb.withdrawTrc10(tokenId, tokenValue, feeLimit, options);

// example
solWeb.withdrawTrc10(100059, 10000000, 100000);
```

###### Arguments

| Parameter  | Description                                     | Type                   | Options  |
| ---------- | ----------------------------------------------- | ---------------------- | -------- |
| tokenId    | Token Id of TRC10                               | Integer                | Required |
| tokenValue | Amount of TRC10 token (Units in SOL) to deposit | Integer (Units in SOL) | Required |
| feeLimit   | Cost limit                                      | Integer, long          | Required |
| options    | The permissions Id                              | Object                 | Optional |

#### withdrawTrc20

###### withdraw trc20 token from side chain to main chain

```javascript
// format
solWeb.withdrawTrc20(num, feeLimit, contractAddress, options);

// example
solWeb.withdrawTrc20(10000, 10000000, 'TWzXQmDoASGodMss7uPD6vUgLHnkQFX7ok');
```

###### Arguments

| Parameter       | Description                                     | Type          | Options  |
| --------------- | ----------------------------------------------- | ------------- | -------- |
| Num             | Num of TRC20                                    | Integer       | Required |
| contractAddress | Side Chain TRC20 Contract Address after mapping | Integer       | Required |
| feeLimit        | Cost limit                                      | Integer, long | Required |
| options         | The permissions Id                              | Object        | Optional |

#### withdrawTrc721

###### withdraw trc721 token from side chain to main chain

```javascript
// format
solWeb.withdrawTrc721(id, feeLimit, contractAddress, options);

// example
solWeb.withdrawTrc721(101, 10000000, 'TA2xrVESq2UcEtDtgPzxNJEiLgxmMVdtFR');
```

###### Arguments

| Parameter       | Description                                     | Type          | Options  |
| --------------- | ----------------------------------------------- | ------------- | -------- |
| id              | Id of TRC721                                    | Integer       | Required |
| contractAddress | Side Chain TRC20 Contract Address after mapping | Integer       | Required |
| feeLimit        | Cost limit                                      | Integer, long | Required |
| options         | The permissions Id                              | Object        | Optional |