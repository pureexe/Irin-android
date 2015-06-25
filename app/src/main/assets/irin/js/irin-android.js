var rs = new RiveScript({
	debug_div: "debug",
	debug:     false
});

var wordcut = require('wordcut');
wordcut.init();

function cutthai(msg){
    var lastchar = msg.charAt(msg.length - 1);
    if(lastchar=="?"&&msg.length!=1){
        msg = msg.substr(0,msg.length-1);
    }
    msg = wordcut.cut(msg);
    msg = msg.replace(/\|/g," ");
    msg = msg.replace("   "," ");
    return msg;
}

var brainfile = ["brain/spellcheck.rive",
				 "brain/irin.rive",
				 "brain/javascript.rive",
				 "brain/android.rive"
				];
function on_load_success (){
	console.log("load ok");
	ChatFragment.setReady();
}
function on_load_error (err) {
	ChatFragment.setError(err);
}
rs.loadFile(brainfile, on_load_success, on_load_error);

function getReply(msg){
	reply = rs.reply("user", cutthai(msg));
	if(reply!=""&&reply!="-"){
		ChatFragment.setIrinMessage(reply);
	}
}