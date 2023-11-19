<template lang="">
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="个人所得税计算"> 
        <div class="title"></div>
        <el-input placeholder="请输入个人收入" v-model="income"></el-input>
        <el-button @click="count">计算</el-button>
        <div class="result" v-show="result">
          您应缴税：{{this.result}}元
        </div>
      </el-tab-pane>
      <el-tab-pane label="计算规则">
        <el-table :data="rules" stripe style="width: 100%">
          <el-table-column prop="total" label="全月应纳税所得额" width="350">
          </el-table-column>
          <el-table-column prop="taxrate" label="税率"> </el-table-column>
          <el-table-column prop="deduction" label="速算扣除数（元）">
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
export default {
  data() {
    return {
      rules: [
        {
          total: "全月应纳税额不超过36000元（41000）",
          taxrate: "3%",
          deduction: "0",
        },
        {
          total: "全月应纳税额超过36000至144000元（149000）",
          taxrate: "10%",
          deduction: "2520",
        },
        {
          total: "全月应纳税额超过144000至300000元（305000）",
          taxrate: "20%",
          deduction: "16920",
        },
        {
          total: "全月应纳税额超过300000至420000元（425000）",
          taxrate: "25%",
          deduction: "31920",
        },
        {
          total: "全月应纳税额超过42000至660000元（665000）",
          taxrate: "30%",
          deduction: "52920",
        },
        {
          total: "全月应纳税额超过660000至960000元（965000）",
          taxrate: "35%",
          deduction: "85920",
        },
        {
          total: "全月应纳税额超过960000元",
          taxrate: "45%",
          deduction: "181920",
        },
      ],
      income: '',
      result:'',
    };
  },
  methods: {
    async count() {
      await this.$http.get('/api/income-tax/calculate?income=' + this.income).then(res => {
        
        this.result = res.data;
        if (typeof this.result === 'number'){
          this.result = this.result.toFixed(2);
        }
        console.log(res);
      }).catch(err => {
        console.log(err);
      })
    },
  },
};
</script>
<style lang="scss" scoped>
.el-tabs {
  width: 800px;
  margin: auto;
  margin-top: 50px;
}

.el-input{
  width: 500px;
  margin-top: 30px;
}
.el-button{
  margin-left: 10px;
}
.result{
  margin-top: 30px;
}
</style>
