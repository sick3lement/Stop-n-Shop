<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
 $id = (int)$_POST['id'];
 
 require_once('dbConnect.php');
 
 $sql = "INSERT INTO favourites (id) VALUES('$id')";
 if(mysqli_query($con,$sql)){
 echo 'Successfully added to the Favourites';
 }else{
 echo 'oops! Please try again!';
 }
 mysqli_close($con);

}