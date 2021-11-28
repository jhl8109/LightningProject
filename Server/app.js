var express = require('express');
const { on } = require('nodemon');
var app = express();
var db_config = require(__dirname + '/config/database.js');
var conn = db_config.init();
var router = express.Router;

db_config.connect(conn);

app.use(express.json());
app.use(express.urlencoded({extended:true}));

app.get('/save', function(req,res) {
    var sql = 'SELECT * FROM tb_lightning';
    conn.query(sql,function(err,results) {
        if (err) console.log('query is not excuted. response fail...\n');
        else {
            console.log("query is successfully excuted... \n");
            var arr = new Array();
            arr.push(results);
            res.json(results);
        }
    })
   
})

app.post('/save', function(req,res){
    var body = req.body;
    console.log(body);
    var sql = 'INSERT INTO tb_lightning (schedule, checked) VALUES(?,?)';
    if (body.checked == "false") {
        body.checked = 0;
    } else {
        body.checked = 1;
    }
    var params = [body.schedule, body.checked];
    console.log(body.schedule, body.checked);
    
    conn.query(sql,params,function(err) {
        if (err) {
            console.log('query is not excuted. insert fail...\n');
            console.log(err);
        }
        else console.log("query is successfully excuted... \n");
    })
    res.send("success");
})

app.listen(3000,function(){
    console.log("Server Start!");
})
