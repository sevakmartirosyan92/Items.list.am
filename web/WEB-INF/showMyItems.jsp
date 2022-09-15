<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>My Items</title>
</head>
<body>

<%
  List<Item> itemList = (List<Item>) request.getAttribute("itemList");
%>

<%for (Item item : itemList) {%>
<figure>
  <img src="/itemImage" width="300px">
  <figcaption>
    <div>title: <%=item.getTitle()%>
    </div>
    <div>category: <%=item.getCategory().getName()%>
    </div>
    <div>price: <%=item.getPrice()%>
    </div>
    <div>owner: <%=item.getUser().getName() + " " + item.getUser().getSurname()%>
    </div>
    <a href="/item/remove">remove item</a>
  </figcaption>
</figure>
<%}%>

</body>
</html>
