<?php
    include("connect.php");

    $id = $_POST['id'];
    $name = $_POST['name'];
    $surname = $_POST['surname'];
    $address = $_POST['address'];
    $section = $_POST['section'];
    $gender = $_POST['gender'];

        $sql = "INSERT INTO labb (id,name,surname,address,section,gender) 
        VALUES ('$id','$name','$surname','$address','$section','$gender')";

        $result = mysqli_query($con,$sql);
        mysqli_close($con);
    
?>