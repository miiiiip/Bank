# Bank
## Create account for users, including: balance, account names, and account number. 

## Users, with accounts that have a balance > 0, are able to withdraw money by:

1. Generating a bill number
2. Concatanate their account number with the bill number (This is I)
3. Create a binary number the same length as I called a.
4. Create two random numbers, c and d, that are the same length as the required length for the hash function g.
5. Create x by inputting a and c into the hash function g, g(a, c).
6. Create y by inputting I⊕a and d into the hash function g, g(I⊕a, d).
7. Input x and y into hash function f, f(x, y).
8. Do steps 2-7 20 times. (20 chunks with I, a, c, d, x, y, blinded chunks = f(x, y))
9. This gets sent to the Bank, and the bank verifies 10 chunks and sends the other 10 back signed blindly.

## Banks are able to verify withdrawls by:

1. Recieving 20 chunks, and asking for 10 of them to be unblinded (Have the individual parts that created each of those 10 chunks be sent as well.)
2. Verify the 10 unblinded chunks are the same and valid. (Just I)
3. Verify that f(x, y) is generated correctly (Reconstruct and check against)
4. Take out money from their balance
5. Send them back the 10 blinded chunks signed with out private key. (f(x,y)^e)

## Users can spend money at some merchant (merchant verifies this some how)

## Merchants then send the bank what they received from the merchant to verify again 

1. Banks get sent 10 chunks that are partially opened, the partially opened chunks were chosen by the merchant.
2. Each chunk either has I⊕a, d, and x or a, c, and y. This is used to generate f(x, y) and if the f(x, y) we generate matches the one given then we know the chunk is valid.
3. Step 2 is done 10 times

(I⊕a = SHA-256(I concatenated to a with a padding of 0's on the left up to 128 bits))


