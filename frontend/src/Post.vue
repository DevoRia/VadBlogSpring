<template>
  <div class="blog" v-if="note">
    <div class="note">
      <h1>{{note.title}}</h1>
      <h2>{{note.author}} | {{note.date}}</h2>
      <p>{{note.text}}</p>
    </div>
  </div>
</template>

<script>
  module.exports = {
    data:function () {
      return {
        posts: notes,
        post: null
      }
    },
    created: function () {
      var postId = this.$route.params.id;
      this.post = this.poster[postId]
    }
  }
</script>
