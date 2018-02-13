import Vue from 'vue'
import VueResource from 'vue-resource'

Vue.use(VueResource);

Vue.component("editable",{
  template: '<a @click="getId" >Редагувати</a>',
  props: {
    //element: Number, //note.id
    title: String,
    author: String,
    text: String
  },
  methods:{
    getId: function () {
      this.$emit('id', 'edit', this.title, this.author, this.text) //передає елемент в transferElement
    }
  }
});

Vue.component('removable',{
  template: '<a @click="getId" >Видалити</a>',
  props: {
    title: String //note.id
  },
  methods:{
    getId: function () {
      this.$emit('id', 'delete', this.title, null, null, null) //передає елемент в transferElement
    }
  }
});

Vue.component('modal', {
  template: '#modal-template',
  methods: {
    remove: function () { //виконується після нажаття кнопки так (діалог для видалення)
      this.$emit('delete');//викликає метод removeById
      this.$emit('close');//вікно ховається
    }
  }
});

var EditModal = Vue.component('modal-edit', {
  template: '#modal-template-edit',
  data:function(){
    return{
    title: App.title,
    author: App.author,
    text: App.text
  }},
  methods:{
    editOnClick: function () {
      this.$emit('edit-method', this.title, this.author, this.text);//викликає метод editPost
      this.$emit('close');//вікно ховається
    },
  }
});

Vue.component('modal-add',{
  template: '#modal-template-add',
  data: function () {
    return {
      title: "",
      author: "",
      text: ""
      }
    },
  methods:{
    addOnClick: function () {
      this.$emit('add', this.title, this.author, this.text);//викликає метод addPost
      this.$emit('close');//вікно ховається
    }
  }
});

var App = new Vue({
  el: '#app',
  data: {
  	endpoint: "http://localhost:8081/server/show",
    removeEndpoint: "http://localhost:8081/server/remove/",
    addEndpoint: "http://localhost:8081/server/add",
    editEndpoint: "http://localhost:8081/server/edit",
    //elementData: 0, //id будь-якого поста
    notes: [],
    showRemoveDialog: false, // видимість діалогового вікна для видалення
    showAddDialog: false,// видимість діалогового вікна для додавання
    showEditDialog: false,// видимість діалогового вікна для редагування
    oldTitle: "",
    title: "",
    author: "",
    text: ""
  },
  components:{
    EditModal
  },
  methods:{
  	getAllPosts: function(){
  		 this.$http.get(this.endpoint).then(function (response){//отримуємо весь список з бек-енда
         this.notes = response.data;
         this.notes.forEach(function (note, i, notes) {//Перетворюємо формат дати
           let date = new Date(note.date);
           notes[i].date = (date.getDate() > 10 ? date.getDate() : "0" +date.getDate())  +
             "-" + ((date.getMonth() + 1)> 10 ? (date.getMonth() + 1) : "0" +(date.getMonth() + 1)) +
             "-" + date.getFullYear() +
             " " + (date.getHours()> 10 ? date.getHours() : "0" + date.getHours()) +
             ":" + (date.getMinutes() > 10 ? date.getMinutes() : "0" + date.getMinutes());
         })
         }, function (error) {
  		      console.log("Неможливо отримати данні. Помилка: " + error.data);
         })
  	},
    removeById: function () {
  	  let removeEndpoint = this.removeEndpoint + this.title;//конкантинуємо повне посилання
      this.$http.get(removeEndpoint).then(function (response) {
        location.reload(true);//перезавантаження сторінки...
      }, function (error) {/*Помилка */});
    },
    transferElement: function (key, title, author, text) {
      this.title = title; //зберігає id в цьому об'єкті
      switch (key) {
        case 'delete':
          this.showRemoveDialog = true; //показує вікно
          break;
        case 'edit':
          this.showEditDialog = true;
          this.title = title;
          this.author = author;
          this.text = text;
          break;
      }
    },
    addPost: function (title, author, text) {
      let body = new FormData();//JSON не валідується, використовуємо цей об'єкт
  	  body.append('title', title);
  	  body.append('author', author);
  	  body.append('text', text);
      this.$http.post(this.addEndpoint, body).then(function (response) {
        location.reload(true);//перезавантаження сторінки...
      }, function (error) {/*Помилка */})
    },
    editPost: function (title, author, text) {
  	  let body = new FormData();
      body.append('title', title);
      body.append('author', author);
      body.append('text', text);
      this.$http.post(this.editEndpoint, body).then(function (response) {
        location.reload(true);
      }, function (error) {/*Помилка */})
    }
  },
  created: function () {
     this.getAllPosts();
  }

});
