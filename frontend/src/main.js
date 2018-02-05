import Vue from 'vue'
import VueResource from 'vue-resource'

Vue.use(VueResource);

Vue.component("editable",{
  template: '<a @click="getId" >Редагувати</a>',
  props: {
    element: Number //note.id
  },
  methods:{
    getId: function () {
      this.$emit('id', this.element, 'edit') //передає елемент в transferElement
    }
  }
});

Vue.component('removable',{
  template: '<a @click="getId" >Видалити</a>',
  props: {
    element: Number //note.id
  },
  methods:{
    getId: function () {
      this.$emit('id', this.element, 'delete') //передає елемент в transferElement
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

Vue.component('modal-edit', {
  template: '#modal-template-edit'
});

Vue.component('modal-add',{
  template: '#modal-template-add',
  data:{
    title: String,
    author: String,
    text: String
  },
  methods:{
    addOnClick: function () {
      this.$emit('add', this.title, this.author, this.text);//викликає метод addPost
      this.$emit('close');//вікно ховається
    }
  }

});

new Vue({
  el: '#app',
  data: {
  	endpoint: "http://localhost:8081/server/show",
    removeEndpoint: "http://localhost:8081/server/remove/",
    addEndpoint: "http://localhost:8081/server/add",
    elementData: 0, //id будь-якого поста
    notes: [],
    showRemoveDialog: false, // видимість діалогового вікна для видалення
    showAddDialog: false,// видимість діалогового вікна для додавання
    showEditDialog: false// видимість діалогового вікна для редагування
  },
  methods:{

  	getAllPosts: function(){
  		 this.$http.get(this.endpoint).then(function (response){//отримуємо весь список з бек-енда
         this.notes = response.data;
         }, function (error) {
  		      console.log("Неможливо отримати данні. Помилка: " + error.data);
         })
  	},
    removeById: function () {
  	  let removeEndpoint = this.removeEndpoint + this.elementData;//конкантинуємо повне посилання
      this.$http.get(removeEndpoint).then(function (response) {
        location.reload(true);//перезавантаження сторінки...
      }, function (error) {

      });
    },
    transferElement: function (element, key) {
      this.elementData = element; //зберігає id в цьому об'єкті
      switch (key) {
        case 'delete':
          this.showRemoveDialog = true; //показує вікно
          break;
        case 'edit':
          this.showEditDialog = true;
          break;
      }
    },
    addPost: function (title, author, text) {
  	  console.log(title + author + text);
  	  var body = new FormData();//JSON не валідується, використовуємо цей об'єкт
  	  body.append('title', title);
  	  body.append('author', author);
  	  body.append('text', text);
      this.$http.post(this.addEndpoint, body).then(function (response) {
        location.reload(true);
      }, function (error) {

      })
    }


  	},
  created: function () {
     this.getAllPosts();
  }

});
