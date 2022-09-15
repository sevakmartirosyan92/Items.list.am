<%@ page import="model.User" %>
<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>homepage</title>
</head>
<body>

<%
    User user = (User) session.getAttribute("user");
    String itemAdded = (String) request.getAttribute("itemAdded");
    List<Item> itemList = (List<Item>) request.getAttribute("itemList");
%>

<%if (user != null) {%>
<h2> Բարև Հարգելի <%=user.getName()%>
</h2>

<div style="margin-left: 600px"><a href="/logout" style="color: red">Ելք</a></div>


<div style="width: 400px; display: flex; justify-content: space-between">
    <%if (itemAdded != null) {%>
    <span style="color: red"><%=itemAdded%></span>
    <%}%>
    <div><a href="/item/add" style="color: limegreen">Ավելացնել հայտարարություն</a></div>
    <br><br><br>
    <div><a href="/myItems/show?userId=<%=user.getId()%>" style="color: limegreen">Իմ հայտարարությունները</a></div>
</div>
<br><br>

<%} else {%>

<div style="width: 110px; display: flex; justify-content: space-between; margin-left: 600px">
    <div><a href="/login" style="color: forestgreen"><h4>Մուտք|</h4></a></div>
    <div><a href="/register" style="color: forestgreen"><h4>Գրանցում|</h4></a></div>
</div>

<%}%>

<div style="display: flex; justify-content: space-between; width: 400px">
    <div><a href="/myItems/show" style="color: brown"><h4>Գլխավոր էջ</h4></a></div>
    <div><a href="/items" style="color: limegreen"><h4>Ավտոմեքենաներ|</h4></a></div>
    <div><a href="/items/show" style="color: limegreen"><h4>Տներ|</h4></a></div>
    <div><a href="/items/show" style="color: limegreen"><h4>Կոմերցիոն|</h4></a></div>
    <div><a href="/items/show" style="color: limegreen"><h4>Կահույք|</h4></a></div>
</div>

<%if (itemList != null) {%>
<%for (Item item : itemList) {%>
<figure>
    <img src="/itemImage?itemImage=<%=item.getPicUrl()%>" width="300px">
    <figcaption>
        <div>title:<%=item.getTitle()%>
        </div>
        <div>category:<%=item.getCategory().getName()%>
        </div>
        <div>price:<%=item.getPrice()%>
        </div>
        <div>owner:<%=item.getUser().getName() + " " + item.getUser().getSurname()%>
        </div>
    </figcaption>
</figure>
<%}%>
<%}%>


</body>
</html>