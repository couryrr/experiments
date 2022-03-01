<?php
    $file = $argv[1];
    $oFile = fopen($file, 'r');
    $res = 0;

    while(($line = fgets($oFile))){
        $res += (int) $line; 
    }
    fclose($oFile);
    echo $res;
?>
