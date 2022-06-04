# What is SolWeb

SolWeb inherits from LiteWeb and services for Sol-network. We  encapsulated two objects (mainchain and sidechain) based on LiteWeb. The methods and attributes in mainchain or sidechain are exactly the same as the liteweb instance. For example, users can use solweb.mainchain.xlt.getBalance() to get balance from the mainchain. Futhermore, we add some new methods which are as follows in SolWeb class so that users can use them to contact between the main chain and the side chain. 

# Installation

<strong>Node.js</strong>

```javascript
npm install solweb
```

or

```javascript
yarn add solweb
```

<strong>Browser</strong>

Then easiest way to use SolWeb in a browser is to install it as above and copy the dist file to your working folder. For example:

```javascript
cp node_modules/solweb/dist/SolWeb.js ./js/SolWeb.js
```

so that you can call it in your HTML page as

```javascript
<script src="./js/SolWeb.js"><script>
```
# Test cases

```javascript
npm run test
```
But before run test cases, you must add some info in test/config.js, such fullnode, solidity node, eventsever and private key.

# Documentation

[SolWeb](http://47.252.84.158:8080/solnetwork/guide/SOLWEB.html#solweb-class)