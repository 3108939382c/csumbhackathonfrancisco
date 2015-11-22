<?php

$servername = "localhost";
$username = "root";
$password = "hackathon";

try {
    $db = new PDO("mysql:host=$servername;dbname=hackathondb", $username, $password);
    // set the PDO error mode to exception
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    //echo "Connected successfully"; 

    $username = $_GET['u'];
    $password = $_GET['p'];

    $result = $db->prepare("SELECT `id` FROM `user` WHERE username = ? AND password = ?");
    $result->execute(array("$username", "$password")); 

    $myflag = false;

    foreach ($result as $row) {
        if (isset($row['id'])) {
            $myflag = true;
        }
    }

    if ($myflag) {
        echo 'goodlogin';
    } else {
        echo 'Bad Username and/or Password.';
    }

}
catch(PDOException $e)
{
    //echo "Connection failed: " . $e->getMessage();
}

?>