<template>
    <div class="container">
      <div class="row">
        <h5>{{ userId}}</h5>
      </div>
      <div style="display:flex; justify-content:space-between">
      <div align="left" style="width:90%">
        <b-form-textarea
        no-resize
        placeholder="내용을 입력해주세요."
        ></b-form-textarea>
      </div>
      <div align="right" class="row justify-content-end">
        <b-button style="height:60%; margin-top:0.8rem; margin-right:1rem">
          등록
        </b-button>
      </div>
      </div>
    </div>
</template>

<script>

export default ({
    name: "CommentForm",
    date(){
        return{
            userId : sessionStorage.getItem("USER_ID")
        }
    }
})
</script>
