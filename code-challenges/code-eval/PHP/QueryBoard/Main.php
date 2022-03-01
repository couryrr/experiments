<?php
/* 
 * Problem:
 * Given a 256 x 256 matrix be able to
 * Add per row some value
 * Add per column some value
 * Query a rows sum of values
 * Quert a column sum of values
 *
 *
 * Solution:
 * Really just make a table and fill in as needed
 * Nothing super fancy I don't think
 *
 * */

$fileName = $argv[1];
$file = fopen($fileName, 'r');
$matrix = array_fill(0, 256, array_fill(0, 256, 0)); 

while(($line = fgets($file))){
    $line = explode(' ', $line);
    switch($line[0]){
        case "SetCol":
            for($row = 0; $row < 256; $row++) {
                $matrix[$line[1]-1][$row] = $line[2];
            }

            break;
        case "SetRow":
            for($col = 0; $col < 256; $col++) {
                $matrix[$col][$line[1]-1] = $line[2];
            }
    
            break;
        case "QueryCol":
            $res = 0;
            for($row = 0; $row < 256; $row++) {
                $res += $matrix[$line[1]-1][$row];
            }

            echo $res . PHP_EOL; 
            break;
        case "QueryRow":
            $res = 0;
            for($col = 0; $col < 256; $col++) {
                $res += $matrix[$col][$line[1]-1];
            }
            
            echo $res . PHP_EOL; 
            break;
        default:
            echo "Something unexpected was given" . PHP_EOL;
            break;
    }

}

?>
