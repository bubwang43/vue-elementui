<template>
  <div id="users">
    <el-form :inline="true" :model="formInline" class="demo-form-inline">

      <el-form-item>
        <el-input v-model="formInline.content" placeholder="研报/图表标题" size="50"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>

    </el-form>

    <el-table :data="tableData" style="width: 100%" v-loading="loading2" element-loading-text="拼命加载中" @selection-change="handleSelectionChange">
      <el-table-column prop="reportTitle" label="研报标题"></el-table-column>
      <el-table-column prop="title" label="图表标题"></el-table-column>
<!--      <el-table-column prop="url" label="图片"></el-table-column>-->
<!--      <el-table-column-->
<!--        prop="url"-->
<!--        label="图表"-->
<!--        sortable-->
<!--        width="180">-->
<!--        &lt;!&ndash;插入图片链接的代码&ndash;&gt;-->
<!--        <template slot-scope="scope">-->
<!--          <img :src="scope.row.url" alt="" style="width: 100px;height: 100px">-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column label="图表" header-align="center" align="center">
        <template slot-scope="scope">
          <el-popover placement="top-start" trigger="click"> <!--trigger属性值：hover、click、focus 和 manual-->
            <a :href="scope.row.url" target="_blank" title="查看最大化图片">
              <img :src="scope.row.url" style="width: 300px;height: 300px">
            </a>
            <img slot="reference" :src="scope.row.url" style="width: 400px;height: 400px; cursor:pointer">
          </el-popover>
        </template>
      </el-table-column>

    </el-table>

    <div class="block">
        <div class="r_page">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[10, 20, 30, 40]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>
    </div>
  </div>
</template>
<style type="text/css" scoped>
  .block {
    margin-top: 20px;
    width: 100%;
  }
  .block .r_btn {
    float: left;
    width: 90px;
  }
  .block .r_page {
     float: right;
  }
</style>
<script type="text/javascript">
  import {getGraphics} from '../api'

  export default {
    data() {
      return {
        tableData: [],
        formInline: {
          content: ''
        },
        currentPage:1,
        total:0,
        pageSize:10,
        dialogFormVisible: false,
        formLabelWidth: '120px',
        form: {
            name: '',
            address: '',
            id:''
        },
        loading2: false,
        selectedOptions: [],
        multipleSelection: []
      }
    },
    mounted() {
      this.$store.dispatch('showHeader');
      this.loadData();
    },
    methods: {
      //加载数据
      loadData() {
        this.loading2 = true;
        var params = {
          pageNumber: this.currentPage,
          pageSize: this.pageSize,
          content: this.formInline.content
        };
        getGraphics(params).then(function(result){
          this.tableData = result.list;
          this.total = result.total;
          this.loading2 = false;
        }.bind(this)).catch(function (error) {
            this.loading2 = false;
            console.log(error);
        }.bind(this));
      },
      //查询
      onSubmit() {
        console.log(this.selectedOptions[1]);
        this.loadData();
      },
      //改变分页大小
      handleSizeChange(val) {
        this.pageSize = val;
      },
      //跳转页数
      handleCurrentChange(val) {
        this.currentPage = val;
        this.loadData();
      }
    }
  }
</script>
