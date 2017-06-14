<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
 $id = (int)$_POST['id'];
 require_once('dbConnect.php');

 $sql = "DELETE FROM favourites WHERE id=$id";
 if(mysqli_query($con,$sql)){
 echo 'Successfully removed from Favourites';
 }else{
 echo 'oops! Please try again!';
 }
 
}