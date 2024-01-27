<template>
  <article class="media">
    <figure class="media-left image is-48x48">
      <img :src="`https://cn.gravatar.com/avatar/${comment.userId}?s=164&d=monsterid`">
    </figure>
    <div class="media-content">
      <div class="content">
        <p>
          <strong>{{ comment.username }}</strong>
          <small class="ml-1">发布于:{{ comment.createTime | date }}</small>
          <br />
          {{ comment.content }}
          <br />
          回复
        </p>
      </div>
    </div>
  </article>
</template>

<script>

export default {
  name: 'LvCommentsItem',
  props: {
    comment: {
      type: Object,
      required: true
    }
  }
}
</script>


