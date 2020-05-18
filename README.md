# Checkout system kata
Implement the code for a checkout system that handles pricing schemes such as “apples cost 50 pence, three apples cost £1.30.”<br/>
Implement the code for a supermarket checkout that calculates the total price of a number of items. <br/>
In a normal supermarket, things are identified using Stock Keeping Units, or SKUs. In our store, we’ll use individual letters of the alphabet (A, B, C, and so on).<br/>
Our goods are priced individually. In addition, some items are multi-priced: buy ‘n’ of them, and they’ll cost you ‘y’ pence.<br/>
For example, item ‘A’ might cost 50 pence individually, but this week we have a special offer: buy three ‘A’s and they’ll cost you £1.30. 

Use these prices for the sample:
<table style="width:100%">
  <tr>
    <th>Item Unit</th>
    <th>Price (pence)</th>
    <th>Special Price (pence)</th>
  </tr>
  <tr>
    <td>A</td>
    <td>50</td>
    <td>3 for 130</td>
  </tr>
  <tr>
    <td>B</td>
    <td>30</td>
    <td>2 for 45</td>
  </tr>
</table>

Our checkout accepts items in any order, so that if we scan a B, an A, and another B, we’ll recognize the two B’s and price them at 45 (for a total price so far of 95).<br/>
Because the pricing changes frequently, we need to be able to pass in a set of pricing rules each time we start handling a checkout transaction.

The solution should allow for items to input at the command line, and allow for a final total to be calculated and for a running total after each item is ‘scanned’.
