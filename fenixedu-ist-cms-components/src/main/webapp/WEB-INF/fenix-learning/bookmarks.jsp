<%--

    Copyright © 2013 Instituto Superior Técnico

    This file is part of FenixEdu IST CMS Components.

    FenixEdu IST CMS Components is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FenixEdu IST CMS Components is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FenixEdu IST CMS Components.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<h1><spring:message code="bookmarks.title"/></h1>

<c:if test="${empty bookmarks}">
	<em><spring:message code="bookmarks.empty.message"/></em>
</c:if>

<c:if test="${not empty bookmarks}">
<table class="table table-condensed">
	<thead>
		<tr>
			<th><spring:message code="channels.label.name"/></th>
			<th></th>
			<th><spring:message code="channels.label.bookmark"/></th>
			<th>RSS</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="category" items="${bookmarks}">
			<tr>
				<td><c:out value="${category.site.name.content}"/></td>
				<td><c:out value="${category.name.content}"/></td>
				<td>
					<c:choose>
						<c:when test="${bookmarks.contains(category)}">
							<spring:message code="label.yes"/> (<a href="${pageContext.request.contextPath}/learning/bookmarks/remove/${category.externalId}"><spring:message code="action.remove"/></a>)
						</c:when>
						<c:otherwise>
							<spring:message code="label.no"/> (<a href="${pageContext.request.contextPath}/learning/bookmarks/add/${category.externalId}"><spring:message code="action.add"/></a>)
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<a href="${category.rssUrl}" data-toggle="tooltip" title="<c:out value='${category.site.name.content}'/> - <c:out value='${category.name.content}'/>" data-placement="left">
						<img src="${pageContext.request.contextPath}/image/rss.svg" width="15" height="15" />
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</c:if>

<c:if test="${not empty courses}">
<h2><spring:message code="label.courses"/></h2>
<table class="table table-condensed">
	<thead>
		<tr>
			<th><spring:message code="channels.label.name"/></th>
			<th></th>
			<th><spring:message code="channels.label.bookmark"/></th>
			<th>RSS</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="course" items="${courses}">
		<c:forEach var="category" items="${course.site.categoriesSet}">
			<tr>
				<td><c:out value="${category.site.name.content}"/></td>
				<td><c:out value="${category.name.content}"/></td>
				<td>
					<c:choose>
						<c:when test="${bookmarks.contains(category)}">
							<spring:message code="label.yes"/> (<a href="${pageContext.request.contextPath}/learning/bookmarks/remove/${category.externalId}"><spring:message code="action.remove"/></a>)
						</c:when>
						<c:otherwise>
							<spring:message code="label.no"/> (<a href="${pageContext.request.contextPath}/learning/bookmarks/add/${category.externalId}"><spring:message code="action.add"/></a>)
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<a href="${category.rssUrl}" data-toggle="tooltip" title="<c:out value='${category.site.name.content}'/> - <c:out value='${category.name.content}'/>" data-placement="left">
						<img src="${pageContext.request.contextPath}/image/rss.svg" width="15" height="15" />
					</a>
				</td>
			</tr>
		</c:forEach>
		</c:forEach>
	</tbody>
</table>
</c:if>
<script>$(function () {
  $('[data-toggle="tooltip"]').tooltip()
});</script>