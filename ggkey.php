<?php 
header("Content-type: text/html; charset=UTF-8");
$tempStr = $_GET['token'];
if(empty($tempStr))die;
require_once 'GoogleAuthenticator.php';
$ga = new PHPGangsta_GoogleAuthenticator();
$ggkey = $ga->getCode($tempStr);
die($ggkey);
