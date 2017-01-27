<?php
echo "<h2> POST Daten </h2>";
var_dump($_POST);
$file = 'hs.json';
$current = stripslashes($_POST['json']);
if(!empty($_POST) || $_POST['json'] != ''){
 file_put_contents($file, $current);
}
?>