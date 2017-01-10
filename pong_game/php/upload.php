<?php
echo "<h2> POST Daten </h2>";
var_dump($_POST);
$file = 'hs.txt';
$current = stripslashes($_POST['json']);
file_put_contents($file, $current);
?>
