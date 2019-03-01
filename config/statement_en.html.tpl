<!-- LEAGUES level1 level2 level3 level4 -->
<div class="statement-body">
  <!-- BEGIN level1 level2 level3 -->
  <div style="color: #7cc576;
        background-color: rgba(124, 197, 118,.1);
        padding: 20px;
        margin-right: 15px;
        margin-left: 15px;
        margin-bottom: 10px;
        text-align: left;">
    <div style="text-align: center; margin-bottom: 6px">
      <img src="//cdn.codingame.com/smash-the-code/statement/league_wood_04.png" />
    </div>
    <p style="text-align: center; font-weight: 700; margin-bottom: 6px;">
      <!-- BEGIN level1 -->
      This is a <b>league based</b> challenge.
      <!-- END -->
      <!-- BEGIN level2 -->
      Welcome to the Wood2 league!
      <!-- END -->
      <!-- BEGIN level3 -->
      Welcome to the Wood1 league!
      <!-- END -->
      <!-- BEGIN level4 -->
      Welcome to the Bronze league!
      <!-- END -->
    </p>
    <span class="statement-league-alert-content">
      <!-- BEGIN level1 -->
      Wood leagues should be considered as a tutorial which lets players discover the different rules of the game. <br>
      In Bronze league, all rules will be unlocked and the real challenge will begin.
      <!-- END -->
      <!-- BEGIN level2 -->
      In Wood 2, customers can order a more complex dessert: chopped strawberries. Strawberries need to be chopped at the chopping board.
      <!-- END -->
      <!-- BEGIN level3 -->
      In Wood 1, customers can order a more complex dessert: croissants. Dough is cooked into croissants at the oven.
      <!-- END -->
      <!-- BEGIN level4 -->
      In Bronze, customers can order an even more complex dessert: blueberry tart. Dough needs to be chopped at the chopping board. Then, blueberries should be added to have a raw tart. The raw tart needs then to be cooked into a blueberry tart at the oven.
      <!-- END -->
    </span>
  </div>
  <!-- END -->

  <!-- GOAL -->
  <div class="statement-section statement-goal">
    <h2>
      <span class="icon icon-goal">&nbsp;</span>
      <span>The Goal</span>
    </h2>
    <div class="statement-goal-content">
      Control a chef and prepare food for customers as quick as possible, and earn more points than other players.
    </div>
  </div>

  <!-- RULES -->
  <div class="statement-section statement-rules">
    <h2>
      <span class="icon icon-rules">&nbsp;</span>
      <span>The Game</span>
    </h2>
    <div class="statement-rules-content">
      <p>
        This is a three-player game played on a grid of size 11*7. A match is played in 3 rounds, each with 2 of the players.
      </p>
      <p>
        Round 1: player A with player B <br>
        Round 2: player A with player C <br>
        Round 3: player B with player C <br>
      </p>
      <p>
        Each player will thus play two rounds in each match. A player total points is the sum of both rounds' points. 
      </p>
      <p>
        Each round lasts for <const>100</const> turns and plays the same kitchen and customers conditions.
      </p>
      <p>
        <b>A round</b>
      </p>
      <p>
        Each player controls a chef who moves around the kitchen and prepares food for customers.
      </p>
      <p>
        Both players play collaboratively, and one after the other.
       </p>
      <p>
        <b>The kitchen</b>
      </p>
      <p>
        The kitchen contains: floor cells (on which the chefs can move) (<const>.</const>), a bin (<const>G</const>, empty tables (<const>#</const>), a dish disposer (<const>D</const>) and a customer window (<const>W</const>).
      </p>
      <!-- BEGIN level1 -->
      <p>
        It also contains different food crates that dispense either blueberries (<const>B</const>) or ice cream (<const>I</const>).
      </p>
      <!-- END -->
      <!-- BEGIN level2 -->
      <div style="color: #7cc576;
        background-color: rgba(124, 197, 118,.1);
        padding: 2px;">
        <p>
          It also contains different food crates that dispense either blueberries (<const>B</const>), ice cream (<const>I</const>), or strawberries (<const>S</const>).
        </p>
        <p>
          It also contains a new appliance: a chopping board (<const>C</const>).
        </p>
      </div>
      <!-- END -->
      <!-- BEGIN level3 -->
      <div style="color: #7cc576;
        background-color: rgba(124, 197, 118,.1);
        padding: 2px;">
        <p>
          It also contains different food crates that dispense either blueberries (<const>B</const>), ice cream (<const>I</const>), strawberries (<const>S</const>) or dough (<const>D</const>).
        </p>
        <p>
          It also contains two different appliances: a chopping board (<const>C</const>) and an oven (<const>O</const>).
        </p>
      </div>
      <!-- END -->
      <!-- BEGIN level4 -->
      <p>
        It also contains different food crates that dispense either blueberries (<const>B</const>), ice cream (<const>I</const>), strawberries (<const>S</const>) or dough (<const>D</const>).
      </p>
      <p>
        It also contains two different appliances: a chopping board (<const>C</const>) and an oven (<const>O</const>).
      </p>
      <!-- END -->
      <p>
        <b>The customers</b>
      </p>
      <p>
        At most 3 customers are waiting for their order. A customer's order can contain 2 to 4 desserts. Each delivered order rewards the team of chefs with points, but the longer the customer waits, the less points they get.
      </p>
      <!-- BEGIN level1 -->
      <p>
        A customer's order should be served on a dish (<const>DISH</const>).
      </p>
      <!-- END -->
      <!-- BEGIN level2 level3 level4-->
      <div style="color: #7cc576;
        background-color: rgba(124, 197, 118,.1);
        padding: 2px;">
        <p>
          A customer's order should be served on a dish (<const>DISH</const>). A dish can only contain (finished) desserts.
        </p>
      </div>
      <!-- END -->
      <p>
        There are maximum 3 dishes in play. As soon as an order is validated, a new dish is made available in the disposer.
      </p>
      <p>
        <b>The desserts</b>
      </p>
      <p>
        The chefs can prepare two basic desserts: blueberries (<const>BLUEBERRIES</const>) and ice cream (<const>ICE_CREAM</const>).
      </p>
      <!-- BEGIN level2 -->
      <div style="color: #7cc576;
        background-color: rgba(124, 197, 118,.1);
        padding: 2px;">
        <p>
          The chefs can also prepare a classic dessert: chopped strawberries (<const>CHOPPED_STRAWBERRIES</const>). 
        </p>
        <p>
          Strawberries need to be cut at the chopping board before being dressed. </br>
          Chopping board: <const>CHOPPED_STRAWBERRIES</const> => (<const>STRAWBERRIES</const>)
        </p>
      </div>
      <!-- END -->
      <!-- BEGIN level3 -->
      <div style="color: #7cc576;
        background-color: rgba(124, 197, 118,.1);
        padding: 2px;">
        <p>
          The chefs can also prepare two classic desserts: chopped strawberries (<const>CHOPPED_STRAWBERRIES</const>) and croissants (<const>CROISSANTS</const>).
        </p>
        <p>
          Strawberries need to be cut at the chopping board before being dressed. </br>
          Chopping board: <const>STRAWBERRIES</const> => (<const>CHOPPED_STRAWBERRIES</const>)
        </p>
        <p>
          A dough needs to be cooked into croissants at the oven before being dressed. </br>
          Oven: <const>DOUGH</const> => <const>CROISSANTS</const>
        </p>
      </div>
      <!-- END -->
      <!-- BEGIN level4 -->
      <p>
          The chefs can also prepare two classic desserts: chopped strawberries (<const>CHOPPED_STRAWBERRIES</const>) and croissants (<const>CROISSANTS</const>).
        </p>
        <p>
          Strawberries need to be cut at the chopping board before being dressed. </br>
          Chopping board: <const>STRAWBERRIES</const> => (<const>CHOPPED_STRAWBERRIES</const>)
        </p>
        <p>
          A dough needs to be cooked into croissants at the oven before being dressed. </br>
          Oven: <const>DOUGH</const> => <const>CROISSANTS</const>
        </p>
      <div style="color: #7cc576;
        background-color: rgba(124, 197, 118,.1);
        padding: 2px;">
        <p>
          The chefs can also prepare one advanced dessert: blueberry tart (<const>TART</const>).
        </p>
        <p>
          A dough needs to be chopped at the chopping board first, then mixed with blueberries and cooked into a blueberry tart in the oven before being dressed. </br>
          Chopping board: <const>DOUGH</const> => <const>CHOPPED_DOUGH</const>
          <const>CHOPPED_DOUGH</const> + <const>BLUEBERRIES</const> => <const>RAW_TART</const>
          Oven: <const>RAW_TART</const> => <const>TART</const>
        </p>
        <p>
          Cooking takes <const>5</const> turns. If food is not taken from the oven after <const>5</const> turns, the food gets burned and must be thrown into the bin.
        </p>
      </div>
      <!-- END -->
      <p>
        <b>Actions</b>
      </p>
      <p>
        To move to a different cell, use the command <action>MOVE x y</action>. The chefs move horizontally and vertically, of <const>4</const> cells at most. They can't pass onto each other.
      </p>
      <p>
        To interact with a cell (x,y), use the command <action>USE x y</action>. If the chef is adjacent to the cell when using the <action>USE</action> command, the action is successful; else, the chef will move closer to that cell. The <action>USE</action> command works diagonally (8-adjacency).
      </p>
      <p>
        Depending on the cell, the <action>USE</action> will have different effects. They're summarized in the table below:
      </p>
      <p>
        To do nothing, use <action>WAIT</action>.
      </p>
    </div>
  </div>

  <!-- Victory conditions -->
  <div class="statement-victory-conditions">
    <div class="icon victory"></div>
    <div class="blk">
      <div class="title">Victory Conditions</div>
      <div class="text">
        <ul style="padding-top: 0;padding-bottom: 0;">
          <li>
            You earn more points than your opponents after three rounds.
          </li>
        </ul>
      </div>
    </div>
  </div>

  <!-- Lose conditions -->
  <div class="statement-lose-conditions">
    <div class="icon lose"></div>
    <div class="blk">
      <div class="title">Loss Conditions</div>
      <div class="text">
        <ul style="padding-top: 0;padding-bottom: 0;">
          <li>
            Your program times out.
          </li>
          <li>
            Your program provides invalid output for the active turn type.
          </li>
        </ul>
      </div>
    </div>
  </div>
  <br>

  <!-- EXPERT RULES -->
  <div class="statement-section statement-expertrules">
    <h2>
      <span class="icon icon-expertrules">&nbsp;</span>
      <span>Advanced Details</span>
    </h2>
    <div class="statement-expert-rules-content">
      <p>
        You can see the game's source code here: <a href=""></a>.
      </p>
      <p>
        <ul style="padding-top: 0;padding-bottom: 0;">
          <li>
            The chefs cannot exchange any food or dish between each other. They need to put it down on a table for the other to pick it up.
          </li>
          <li>
            The chefs cannot put food on the floor.
          </li>
          <li>
            The chefs cannot pick up a dish if they're already carrying one.
          </li>
          <li>
            As soon as food is put on a dish, it cannot be removed from it. To empty a plate, USE the bin.
          </li>
          <li>
            For every turn a customer waits for an order, the reward is decreased by 1.
          </li>
          <li>
            A dish cannot contain more than 4 desserts.
          </li>
        </ul>
      </p>
    </div>
  </div>
  <!-- PROTOCOL -->
  <div class="statement-section statement-protocol">
    <h2>
      <span class="icon icon-protocol">&nbsp;</span>
      <span>Game Input</span>
    </h2>

    <!-- Protocol block -->
    <div class="blk">
      <div class="title">Input for the first turn</div>
      <div class="text">
        <span class="statement-lineno">First line</span>: an Integer <var>numAllCustomers</var> for the total number of customers (same list of customers for each round).
        <span class="statement-lineno">Next <var>numAllCustomers</var> lines</span>:
        <ul style="margin-top: 0;padding-bottom: 0;">
          <li>
            A String <var>customerItem</var> for the customer's order </br>
            Ex: <const>DISH-BLUEBERRIES-ICE_CREAM</const>
          </li>
          <li>
            An Integer <var>customerAward</var> for the number of points awarded if the customer's order is delivered
          </li>
        </ul>
        <span class="statement-lineno">Next <const>7</const> lines</span>: A String <var>kitchenLine</var> of size 11 representing a part of the kitchen. </br>
        <ul style="margin-top: 0;padding-bottom: 0;">
          <li>
            <const>.</const>: walkable cell
          </li>
          <li>
            <const>D</const>: the dish dispenser
          </li>
          <li>
            <const>W</const>: the window
          </li>
          <li>
            <const>B</const>: the blueberry crate
          </li>
          <li>
            <const>I</const>: the ice cream crate
          </li>
          <!-- BEGIN level2 level3 level4 -->
          <li>
            <const>S</const>: the strawberry crate
          </li>
          <li>
            <const>C</const>: the chopping board
          </li>
          <!-- END -->
          <!-- BEGIN level3 level4 -->
          <li>
            <const>D</const>: the dough crate
          </li>
          <li>
            <const>O</const>: the oven
          </li>
          <!-- END -->
        </ul>
      </div>
    </div>
    <div class="blk">
      <div class="title">Input for one game turn</div>
      <div class="text">
        <span class="statement-lineno">First line</span>: An Integer <var>turnsRemaining</var> for the number of turns remaining before the end of the current round. </br>
        <span class="statement-lineno">Next <const>3</const> lines</span>: 
        <ul style="margin-top: 0;padding-bottom: 0;">
          <li>
            Two Integers <var>playerX</var> and <var>playerY</var> for the player's chef position
          </li>
          <li>
            A String <var>playerItem</var> for what the player's chef is carrying </br>
            Ex: <const>DISH-BLUEBERRIES-ICE_CREAM</const>
          </li>
        </ul>
        <span class="statement-lineno">Next <const>3</const> lines</span>: 
        <ul style="margin-top: 0;padding-bottom: 0;">
          <li>
            Two Integers <var>partnerX</var> and <var>partnerY</var> for the other player's chef position
          </li>
          <li>
            A String <var>partnerItem</var> for what the other player's chef is carrying
          </li>
        </ul>
        <span class="statement-lineno">Next line</span>: An Integer <var>numTablesWithItems</var> for the number of non-empty tables </br>
        <span class="statement-lineno">Next <var>numTablesWithItems</var> lines</span>:
        <ul style="margin-top: 0;padding-bottom: 0;">
          <li>
            Two Integers <var>tableX</var> and <var>tableY</var> for the table's position
          </li>
          <li>
            A String <var>item</var> for what's on the table. </br>
            Ex: <const>DISH-BLUEBERRIES-ICE_CREAM</const>
        </ul>
        <!-- BEGIN level1 level2 -->
        <span class="statement-lineno">Next line</span>: to ignore in this league </br>
        <!-- END -->
        <!-- BEGIN level3 level4 -->
        <span class="statement-lineno">Next line</span>: A String <var>ovenContents</var> for what's in the oven and an Integer <var>ovenTimer</var> for the number of turns the food has been put in the oven. </br>
        <!-- END -->
        <span class="statement-lineno">First line</span>: an Integer <var>numCustomers</var> for the current number of customers waiting for their order. </br>
        <span class="statement-lineno">Next <var>numCustomers</var> lines</span>:
        <ul style="margin-top: 0;padding-bottom: 0;">
          <li>
            A String <var>customerItem</var> for the customer's order </br>
            Ex: <const>DISH-BLUEBERRIES-ICE_CREAM</const>
          </li>
          <li>
            An Integer <var>customerAward</var> for the number of points awarded if the customer's order is delivered
          </li>
        </ul>
      </div>
    </div>

    <!-- Protocol block -->
    <div class="blk">
      <div class="title" style="padding-bottom: 0;">Output for a turn</div>
      <div class="text">
        <ul style="margin-top: 0;margin-bottom: 0;padding-bottom: 0;">
          <li>
            <action>MOVE x y</action> to move to the cell (x,y).
          </li>
          <li>
            <action>USE x y</action> to interact with the cell (x,y).
          </li>
          <li>
            <action>WAIT</action> to do nothing.
          </li>
        </ul>
      </div>
    </div>
  </div>

  <!-- Constraints block -->
  <div class="blk">
    <div class="title">Constraints</div>
    <div class="text">
      Response time for the first turn ≤ <const>1</const>s<br>
      Response time per turn ≤ <const>50</const>ms<br>
    </div>
  </div>

  <!-- BEGIN level1 level2 level3-->
  <div style="color: #7cc576;
      background-color: rgba(124, 197, 118,.1);
      padding: 20px;
      margin-top: 10px;
      text-align: left;">
    <div style="text-align: center; margin-bottom: 6px">
      <img src="//cdn.codingame.com/smash-the-code/statement/league_wood_04.png" />
    </div>
    <p style="text-align: center; font-weight: 700; margin-bottom: 6px;">
      What is in store in the higher leagues?
    </p>
    <p>
      The extra rules available in higher leagues are:
      <ul style="margin-top: 0;padding-bottom: 0;" class="statement-next-rules">
        <!-- BEGIN level1 -->
        <li>In Wood 2, chefs can cut strawberries at the chopping board.</li>
        <!-- END -->
        <!-- BEGIN level1 level2 -->
        <li>In Wood 1, chefs can cook a dough into croissants.</li>
        <!-- END -->
        <!-- BEGIN level1 level2 level3 -->
        <li>In Bronze, chefs can prepare blueberry tarts.</li>
        <!-- END -->
      </ul>
    </p>
  </div>
  <!-- END -->

</div>
