<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>02.basic</title>
<link rel="stylesheet" type="text/css" href="ext3.0/ext-all.css" />
<script type="text/javascript" src="ext3.0/ext-base.js"></script>
<script type="text/javascript" src="ext3.0/ext-all.js"></script>
<script type="text/javascript">
	Ext.onReady(function() {
		Person = function(name) {
			this.name = name;
			this.addEvents("walk", "eat", "sleep");
		}
		Ext.extend(Person, Ext.util.Observable, {
			info : function(event) {
				return this.name + ' is ' + event + 'ing.';
			}
		});
		Ext.get('walk').on('click', function() {
			person.fireEvent('walk');
		});

		Ext.get('eat').on('click', function() {
			person.fireEvent('eat', '早餐', '中餐', '晚餐');
		});

		Ext.get('sleep').on('click', function() {
			person.fireEvent('sleep', new Date());
		});

		var person = new Person('Lingo');
		person.on('walk', function() {
			Ext.Msg.alert('event', person.name + "在走啊走啊。");
		});
		person.on('eat', function(breakfast, lunch, supper) {
			Ext.Msg.alert('event', person.name + "要吃" + breakfast + "，" + lunch
					+ "和" + supper + "。");
		});
		person.on('sleep', function(time) {
			Ext.Msg.alert('event', person.name + "从" + time.format("H")
					+ "点开始睡觉啦。");
		});

	});
</script>
</head>
<body>
	<button id="walk">walk</button>
	<button id="eat">eat</button>
	<button id="sleep">sleep</button>
</body>
</html>
