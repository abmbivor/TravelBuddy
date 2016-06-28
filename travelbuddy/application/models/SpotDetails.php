<?php
defined('BASEPATH') OR exit('No direct script access allowed');

Class SpotDetails extends CI_model
{

	public function getDescription($spotname)
	{
			$q="select TEXT from description where TOURIST_SPOT_NAME='$spotname'";
  			$query=$this->db->query($q);
  			return $query->result_array();
	}

	public function getUrl($spotname)
	{
			$q="select PHOTO_URL from image where TOURIST_SPOT_NAME='$spotname'";
  			$query=$this->db->query($q);
  			return $query->result_array();
	}
	
	public function getReview($spotname)
	{
			$q="select REVIEW,USER_NAME,date_format(DATE_TIME,'%D %b %Y,%I:%i:%s %p')as DATE_TIME from review where TOURIST_SPOT_NAME='$spotname'";
  			$query=$this->db->query($q);
  			return $query->result();
	}	

}