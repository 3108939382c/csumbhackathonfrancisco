<?php

header('Content-Type: text/html; charset=utf-8');

$servername = "localhost";
$username = "root";
$password = "hackathon";

try {
    $db = new PDO("mysql:host=$servername;dbname=hackathondb;charset=utf8", $username, $password);
    // set the PDO error mode to exception
    //echo "Connected successfully"; 

    $deck = $_GET['d'];

    $queryString = "SELECT `english`, `chinese` FROM " . $deck;

    $result = $db->prepare($queryString);
    $result->execute(); 

    $s = '{"deckContent":[';

    foreach ($result as $row) {
        $s .= '{"e":"' . $row['english'] . '", "c":"' . $row['chinese']. '"},';
    }

    //remove last character
    $s = rtrim($s, ",");

    $s .= ']}';

    echo $s;

}
catch(PDOException $e)
{
    //echo "Connection failed: " . $e->getMessage();
}

?>