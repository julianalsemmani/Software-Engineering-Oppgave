<template id="startup-frontpage">
  <div id="startup-front">
    <div class="container">
      <header>
          <div class="logo">
            <h1 class="logo-text"><a class="link-h" href="index.html">{{ startupInformation.startUpName }}</a></h1>
            <p>Currently v1.0.0</p>
          </div>
      </header>
    <div class="clr"></div>
    <section class="content">

        <div class="image">
          <!--                <img src="https://purepng.com/public/uploads/large/purepng.com-disney-logologobrand-logoiconslogos-251519939495wtv86.png" alt="">-->
          <h1>Startup LOGO HERE</h1>
        </div>

        <h2>Sale and Auctions made easy.</h2>

        <div class="buttons">
          <a href="#" class="btn">Get Started</a>
          <a href="#" class="btn-whi">Sign in</a>
        </div>
      </div>
    </section>
    <div class="clr"></div>
    <footer id="startup-footer">
      <p>{{ startupInformation.address }}, phone: {{ startupInformation.phoneNumber }}</p>
      <p id="copyright">Copyright &copy; <a class="link-h" href="index.html">Startup Bedrift</a>. All rights reserved.</p>
      <p>Website made with ❤️ by <a class="link-h" href="https://github.com/julianalsemmani/EnklereCSS">Julian Alsemmani</a></p>
    </footer>
  </div>
</template>

<script>
Vue.component("startup-frontpage", {
  template: "#startup-frontpage",
  data: () => ({
    startupInformation: []
  }),
  created() {
    fetch(`/api/startup`)
        .then(res => res.json())
        .then(res => { this.startupInformation = res; console.log(res); })
        .catch(() => alert("Data not found"));
  }
});

/* Set the width of the side navigation to 250px */
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

/* Set the width of the side navigation to 0 */
function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}
</script>

<style scoped>
#startup-front {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Raleway', sans-serif;
}

a {
  text-decoration: none;
  color: #000000;
}

.link-h {
  color: #574e6d;
}


/* Changed Background color from #000 #574e6d */
.btn {
  color: #fff;
  background: #574e6d;
  border: 1px solid #574e6d;
  padding: 15px 30px;
  margin: 0px 20px;
}

.btn-whi {
  color: #574e6d;
  background: #fff;
  border: 1px solid #574e6d;
  padding: 15px 30px;
  margin: 0px 20px;
}

.btn:hover {
  color: #fff;
  background: #43405d;
  border: 1px solid #574e6d;
  transition: 0.3s ease;
}

.btn-whi:hover {
  color: #574e6d;
  background: #dddddd;
  border: 1px solid #dddddd;
  transition: 0.3s ease;
}

.container {
  max-width: 80%;
  margin: 0 auto;
}


.clr {
  clear: both;
}

/* The side navigation menu */
.logo {
  float: left;
  padding-top: 15px;
}

.logo h1 {
  color: #574e6d;
}

/* Content Section */

.content {
  margin-top: 50px;
  text-align: center;
}

.image img {
  width: 419px;
  height: 73px;

}

.content h2 {
  padding-top: 80px;
  font-size: 40px;
  max-width: 800px;
  margin: 0 auto;
  font-weight: 100;
}


.codeblock .link {
  color: #73f7dd;
}

.codeblock .code-content {
  color: #ffdc76;
}

.content .buttons {
  margin: 100px 0px;
}

#startup-footer {
  margin-top: 135px;
  margin-bottom: 10px;
}

#startup-footer p {
  text-align: center;
}

#startup-footer .copyright {
  font-size: 18px;
  text-align: center;
}

</style>
