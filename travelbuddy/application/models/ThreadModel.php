<?php

Class ThreadModel extends CI_Model
{

	public function storeThread($text,$username)
	{
		$q="INSERT INTO `question`(`QUESTION_TEXT`,`USER_NAME`,`DATE_TIME`) VALUES ('$text','$username',NOW())";
		$query=$this->db->query($q);
	}

	public function getThreadList()
	{

		$q="select *,date_format(DATE_TIME,'%D %b %Y,%I:%i:%s %p')as DATE_TIME from question order by QUESTION_NO DESC";
		$query=$this->db->query($q);
		return $query->result();
	}

	public function getThread($thread)
	{
		$q="select QUESTION_TEXT,USER_NAME,date_format(DATE_TIME,'%D %b %Y,%I:%i:%s %p')as DATE_TIME from question where QUESTION_NO='$thread'";
		$query=$this->db->query($q);
		return $query->result_array();

	}

	public function getThreadComments($thread)
	{

		$q="select ANSWER_TEXT,USER_NAME,date_format(DATE_TIME,'%D %b %Y,%I:%i:%s %p')as DATE_TIME from answer where QUESTION_NO='$thread'";
		$query=$this->db->query($q);
		return $query->result();

	}

	public function postComment($thread,$text,$username)
	{
		$q="INSERT INTO `answer`(`QUESTION_NO`,`ANSWER_TEXT`,`USER_NAME`,`DATE_TIME`) VALUES ('$thread','$text','$username',NOW())";
		$query=$this->db->query($q);
	}


}