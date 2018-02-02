<template>
  <div class="blog" v-for="post in posts">
    <h2>{{post.author}}</h2>
    <h2>{{post.date}}</h2><br>
    <h1>{{post.title}}</h1>
  </div>
</template>

<script>
  var poster = require('./getAllPosts');
  module.exports = {
    data:function () {
      return {
        posts: poster
      }
    }
  }
</script>
