import Vue from 'vue'
import VueResource from 'vue-resource'
//import VueRouter from 'vue-router'

//import Post from './Post.vue'


Vue.use(VueResource);
//Vue.use(VueRouter);

/*var router = new VueRouter({
  routes:[
  {path: '/post/:id', name:'note', component: Post}
  ]});*/


new Vue({
  el: '#app',
//  router: router,
  data: {
  	endpoint: "http://localhost:8081/server/show",
    show: false,
    notes: []
  },
  methods:{

  	getAllPosts: function(){
  		 this.$http.get(this.endpoint).then(function (response){//отримуємо весь список з бек-енда
         this.notes = response.data;
         }, function (error) {
  		      console.log("Неможливо отримати данні. Помилка: " + error.data);
         })
  	},

  	},
  created: function () {
     this.getAllPosts();
      log.console(notes);
  }

});
