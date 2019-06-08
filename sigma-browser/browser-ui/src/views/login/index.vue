<template>
  <div>
    <header class="sso-header" />
    <section class="content_box">
      <div class="qrcode_content">
        <div class="qrcode_img"></div>
        <div class="mobile_info"></div>
      </div>
      <div class="left_area"></div>
      <div class="divider"></div>
      <div class="right_area"></div>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      msg: 'ceshi'
    }
  }
}
</script>

<style lang="scss" scoped>

.sso-header {
  width: 100%;
  height: 360px;
  background-color: #00b38a;
}
.content_box {
  width: 552px;
  height: 100px;
  margin: -205px auto 60px;
  background-color: #fff;
  padding: 62px 70px 68px 78px;
  border: 1px solid #dce1e6;
  position: relative;
  z-index: 1;
  .qrcode_content {
    position: absolute;
    top: 6px;
    left: 6px;
    cursor: pointer;
    .qrcode_img {
      width: 52px;
      height: 52px;
      background: url('//www.lgstatic.com/passport/static/common/static/img/qrcode-normal_bb3ab97.svg') left center no-repeat;
      float: left;
    }
    .mobile_info {
      width: 106px;
      height: 34px;
      position: absolute;
      left: 48px;
      background: url('//www.lgstatic.com/passport/static/common/static/img/wx-login_c936a5c.svg') left center no-repeat;
      animation: infoMove .6s;
      @keyframes infoMove {
        from {left:150px;}
        to {left:40px;}
      }
    }
  }
}
</style>
