<?php
    include("connect.php");

    $id = $_POST['id'];
    $name = $_POST['name'];
    $surname = $_POST['surname'];
    $address = $_POST['address'];

        $sql = "INSERT INTO labb (id,name,surname,address) 
        VALUES ('$id','$name','$surname','$address')";

        $result = mysqli_query($con,$sql);
        mysqli_close($con);
    
?>