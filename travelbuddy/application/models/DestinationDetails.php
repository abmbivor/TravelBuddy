<?php
defined('BASEPATH') OR exit('No direct script access allowed');

Class DestinationDetails extends CI_model
{

		public function getTransport($source,$destination)
		{
			$q="select DISTINCT(TRANSPORT_NAME),TRANSPORT_TYPE  from transport where (SOURCE='$source' and DESTINATION='$destination') or (SOURCE='$destination' and DESTINATION='$source')";
  			$query=$this->db->query($q);
  			return $query->result();
		}	
		// public function getTransportName($source,$destination)
		// {
		// 	$q="select DISTINCT(TRANSPORT_NAME) from transport where (SOURCE='$source' and DESTINATION='$destination') or (SOURCE='$destination' and DESTINATION='$source')";
  // 			$query=$this->db->query($q);
  // 			return $query->result();
		// }

		// public function getTransportType($source,$destination)
		// {
		// 	$q="select DISTINCT(TRANSPORT_TYPE) from transport where SOURCE='$source' and DESTINATION='$destination'";
  // 			$query=$this->db->query($q);
  // 			return $query->result();
		// }

		public function getHotel($destination)
		{
			$q="select HOTEL_NAME from hotel where DISTRICT_NAME='$destination'";
  			$query=$this->db->query($q);
  			return $query->result_array();
		}

		public function getTouristSpot($destination)
		{
			$q="select TOURIST_SPOT_NAME from tourist_spot where DISTRICT_NAME='$destination'";
  			$query=$this->db->query($q);
  			return $query->result();
		}


}