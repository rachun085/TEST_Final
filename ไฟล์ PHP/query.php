<?php
    include("connect.php");

        $sql = "SELECT * FROM labb";
        $result = array();
        $query = mysqli_query($con,$sql);

    while($row = mysqli_fetch_assoc($query)){
        array_push($result,array("id" => $row["id"],
                    "name" => $row["name"],
                    "surname" => $row["surname"],
                    "surname" => $row["surname"],
                    "address" => $row["address"],
                    "section" => $row["section"],
                    "gender" => $row["gender"]));
    }

    print json_encode(array("result" => $result));

    mysqli_close($con);

    ?>