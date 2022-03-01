<?php
    $fileName = $argv[1];
    $file = fopen($fileName, 'r');
    while(($line = fgets($file))){
        $pair = explode(',', $line);
        
        $N = intval($pair[0]);
        $M = intval($pair[1]);
        echo $N - ($M * intval($N/$M)) . PHP_EOL;
    }


?>
