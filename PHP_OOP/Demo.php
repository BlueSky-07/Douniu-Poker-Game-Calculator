<?php
include 'Poker.php';
$game = new Poker();
$game->gameStart($_SERVER['QUERY_STRING']);
//$game->gameStart('12345');
echo $game->toJSON();
?>