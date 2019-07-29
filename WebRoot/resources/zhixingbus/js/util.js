function getMinute(t){
	if (t.length<1||t < 0) {
		return "--";
	} else {
		return parseInt(t / 60);
	}
}
function getSeconds(t){
	if (t.length<1||t < 0) {
		return "--";
	} else {
		return t % 60;;
	}
}
function currentTime(){
	var d = new Date(),str = '';
	 str += d.getFullYear()+'年';
	 str  += d.getMonth() + 1+'月';
//	 str  += d.getDate()+'日';
//	 str += d.getHours()+'时'; 
//	 str  += d.getMinutes()+'分'; 
//	str+= d.getSeconds()+'秒'; 
	return str;
}