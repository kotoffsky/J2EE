<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="vues/fragments/header.jsp" />
	<header class="jumbotron hero-spacer">
		<p>${message}</p>
	    <h1>A Warm Welcome!</h1>
	    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ipsa, ipsam, eligendi, in quo sunt possimus non incidunt odit vero aliquid similique quaerat nam nobis illo aspernatur vitae fugiat numquam repellat.</p>
	    <p><a class="btn btn-primary btn-large">Call to action!</a>
	    </p>
	</header>

  <div class="list-group">
    <a href="#" class="list-group-item">
      <h4 class="list-group-item-heading">List group item heading</h4>
      <p class="list-group-item-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima non atque iusto maiores eveniet quidem iste inventore accusantium quo labore cumque excepturi fugiat sequi eaque error architecto ea nemo, obcaecati ducimus ad quae voluptatibus praesentium consequuntur. Nihil, accusamus placeat mollitia aut eum facere iure numquam quasi nulla praesentium magni rerum nobis provident voluptas ea et esse temporibus quo similique minima. Impedit nulla odio illo necessitatibus culpa, assumenda id, quo accusamus adipisci consectetur sint in ducimus fugiat qui nobis deleniti iste. Inventore natus magni corrupti repudiandae cumque? Molestias dignissimos dolore eos consequatur nulla sit? Libero placeat, aliquid accusamus delectus natus incidunt.
      </p>
      <button type="button" class="btn btn-default">Look up profile</button>
    </a>
    <a href="#" class="list-group-item">
      <h4 class="list-group-item-heading">List group item heading</h4>
      <p class="list-group-item-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima non atque iusto maiores eveniet quidem iste inventore accusantium quo labore cumque excepturi fugiat sequi eaque error architecto ea nemo, obcaecati ducimus ad quae voluptatibus praesentium consequuntur. Nihil, accusamus placeat mollitia aut eum facere iure numquam quasi nulla praesentium magni rerum nobis provident voluptas ea et esse temporibus quo similique minima. Impedit nulla odio illo necessitatibus culpa, assumenda id, quo accusamus adipisci consectetur sint in ducimus fugiat qui nobis deleniti iste. Inventore natus magni corrupti repudiandae cumque? Molestias dignissimos dolore eos consequatur nulla sit? Libero placeat, aliquid accusamus delectus natus incidunt.</p>
      <button type="button" class="btn btn-default">Look up profile</button>
    </a>
  </div>

  <script type="text/javascript">
    $("a.list-group-item").click(function(){
      $('.list-group-item').removeClass('active');
      $('.list-group-item-text').hide();
      $(this).addClass('active');
      $('.list-group-item-text',this).show();
    });
  </script>

<jsp:include page="vues/fragments/footer.jsp" />