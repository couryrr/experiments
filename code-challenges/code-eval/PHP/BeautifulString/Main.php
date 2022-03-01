<?php
/*
 * Problem:
 * Find the max value of a string
 * Each number has a value 1 - 26 (inclusive)
 * Case does not matter
 * No letter has the same value
 *
 *
 * Soultion:
 * Get each letter and count
 * From max to least assign highest value 26 to lowest.
 **/
$fileName = $argv[1];
$file = fopen($fileName, 'r');
while(($line = fgets($file))){
    //Case does not matter
    $line = strtoupper($line);
    //No punctuation or spaces
    $line = preg_replace("/[^a-zA-Z]+/",'', $line);
    
    //Take line make it an array and count occurance
    $line = array_count_values(str_split($line));
    
    arsort($line);

    $result = 0;
    $value  = 26;
    foreach($line as $k => $v){
        $result += $v * $value;
        $value--;
    }
    echo $result . PHP_EOL;
}

?>
