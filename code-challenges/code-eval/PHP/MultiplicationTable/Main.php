<?php

for($i = 1; $i <= 12; $i++){
    for($j = 1; $j <= 12; $j++){
        $v =  $i * $j;
        $l = 4 - strlen((string) $v);
        if($j == 1){
            if($v < 10){
                echo " " . $v;
            } else{
                echo $v;
            }
        } else {
            echo str_repeat(" ", $l) . $v;
        }
    }
    echo PHP_EOL;
}


?>
