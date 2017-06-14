<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
 $name = $_POST['name'];
 $image = $_POST['image'];
 $password = $_POST['password'];
 $email = $_POST['email'];
 
 if($name == '' || $email == ''){
 echo 'please fill all values';
 }else{
 require_once('dbConnect.php');
 $sql = "SELECT * FROM users WHERE email='$email'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$sql));
 
 if(isset($check)){
 echo 'username or email already exist';
 }else{ 
 $sql = "INSERT INTO users (name,email,password,image) VALUES('$name','$email','$password','$image')";
 if(mysqli_query($con,$sql)){
 echo 'successfully registered';
 }else{
 echo 'oops! Please try again!';
 }
 }
 mysqli_close($con);
 }
}else{
echo 'error';
}