<?php

$servername = "localhost";
$username = "root";
$password = "hackathon";

try {
    $db = new PDO("mysql:host=$servername;dbname=hackathondb", $username, $password);
    //echo "Connected successfully"; 


    $result = $db->prepare("SELECT `name` FROM `decks`");
    $result->execute(); 

    $s = '{"decks":[';


    foreach ($result as $row) {
        //$row['name']
        $s .= '{"deckName":"' . $row['name'] . '"},';
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