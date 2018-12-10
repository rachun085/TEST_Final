<?php
    $host="localhost";
    $user="root";
    $pass="";
    $db="labb";

    $con = mysqli_connect($host,$user,$pass,$db);
    
    mysqli_query($con,"SET NAMES utf8");
    
    ?>