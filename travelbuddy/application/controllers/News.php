<?php
class News extends CI_Controller {

        public function __construct()
        {
                parent::__construct();
                $this->load->model('news_model');
                $this->load->helper('url_helper');
        }

        public function index()
        {
                #$ss=$this->news_model->storeUser('bivor','bivor@gmail.com','jjjjjjjjjj');
                $data['user'] = $this->news_model->get_news();
                $data['title'] = 'User archive';
               // $ans=array($this->news_model->get_news('Shamik'));
                //$ans=$this->news_model->get_news('shamikroy','1234');
                echo "Records from the Database<br/>";
               # $count=count($ans);
               # echo $count;
                #echo $ans;
               # if($ans>0)echo "got something";
               # else echo "nothing";
                foreach ($data['user'] as $key) {
                        echo $key->EMAIL_ID."<br/>";
                }
               
               /* $this->load->view('templates/header', $data);
                $this->load->view('news/index', $data);
                $this->load->view('templates/footer');*/
        }      
}