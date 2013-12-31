Person = function(name){
	this.name = name;
	this.addEvents = ("walk","eat","sleep");
};
Ext.extend(Person, Ext.util.Observable,{
	info: function(event){
		return this.name + 'is'+ event+'ing';
	}
});