<template>
  <div>
    <div class="app-container">
      <div class="main">更新中...</div>
      <div class="rope" />
      <div class="nail top" />
      <div class="nail left" />
      <div class="nail right" />
    </div>
  </div>
</template>

<style lang="scss" scoped>
* {
  margin: 0;
  padding: 0;
}
.app-container {
  width: 400px;
  height: 300px;
  margin: 175px auto;
  position: relative;
  transform: scale(1) rotate(7deg) translate(-17px, 1px);
  animation: 1s init, 0.7s xiuzheng 1s, 3s swing 1.7s infinite;
  .main {
    width: 100%;
    height: 200px;
    background: #F56C6C;
    position: absolute;
    bottom: 0;
    text-align: center;
    line-height: 200px;
    font-family: "Lato", sans-serif;
    font-size: 80px;
    font-weight: bold;
    color: white;
    border-radius: 15px / 20px;
    text-shadow: 0 2px #700a03;
  }
  .rope {
    width: 150px;
    height: 150px;
    position: absolute;
    left: 122px;
    top: 38px;
    transform: rotate(45deg);
    border: solid 5px #ad5700;
    border-bottom: none;
    border-right: none;
  }
  .nail {
    width: 25px;
    height: 25px;
    position: absolute;
    border-radius: 25px;
    &.top {
      background: #9f9f9f;
      top: 0;
      left: 185px;
    }
    &.left {
      top: 110px;
      left: 80px;
    }
    &.right {
      top: 110px;
      right: 80px;
    }
     &.left, &.right  {
      background: #9c0a00;
    }
  }
}
@keyframes swing {
  0% {transform: rotate(7deg) translate(-17px, 1px)}
  50% {transform: rotate(-7deg) translate(17px, 1px)}
  100% {transform: rotate(7deg) translate(-17px, 1px)}
}
@keyframes init {
  from {transform: scale(0)}
  to {transform: scale(1)}
}
@keyframes xiuzheng {
  0% {transform: scale(1);}
  100% {transform: scale(1) rotatez(7deg) translate(-17px, 1px)}
}
</style>
