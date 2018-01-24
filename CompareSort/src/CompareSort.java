public class CompareSort {
    public static void main(String[] args){
        int test[]={1,3,423,-534,346,214,543,64,24};
        CompareSort compareSort=new CompareSort();
        compareSort.quickSort(test,0,test.length);
        for (int a:test
             ) {
            System.out.print(a+"  ");
        }
    }
    /**
     * 冒泡排序
     * 思路：从小到大排序，遍历长度为n的a数组，比较相邻的数组元素的大小，如果前一个元素大于后一个元素，则交换位置，n-1次比较后，数组最后一个元素及为最大元素；
     * 第二次遍历，这次比较n-2次及得到数组第二大的元素就排好了序。
     * 以此类推需经过n次遍历。由此可得需要嵌套的两层遍历。
     * 时间复杂度：O(n^2)*/
    public int [] bubblingSort(int [] a){
        int b=0;
        for (int i = 0; i <a.length ; i++) {
            for (int j = 0; j < a.length-i-1; j++) {
                if (a[j]>a[j+1]) {
                    b=a[j+1];
                    a[j+1]=a[j];
                    a[j]=b;
                }
            }
        }
        return a;
    }
    /**
     * 选择排序
     * 思路：从小到大排序，遍历长度为n的a数组，找出最大的元素，将该元素扔到数组最右边，即完成一次排序，期间需要对n个元素进行扫描，第二次同理，需对
     * n-1个元素进行遍历，以此类推。
     * 时间复杂度:O(n^2)*/
    public int [] selectSort(int [] a){
        for (int i = 0; i <a.length; i++) {
            int maxIndex=0;
            for (int j = 0; j <a.length-i ; j++) {
                if(a[j]>a[maxIndex]) {
                    maxIndex=j;
                }
            }
            int temp=a[a.length-1-i];
            a[a.length-1-i]=a[maxIndex];
            a[maxIndex]=temp;
        }
        return a;
    }
    /**
     * 归并排序
     * 思路：从小到大排序，将长度为n的a数组，分解成n/2的数组，将n/2的数组继续分解，当出现数组元素只有2个或1个时停止；交换这些数组的
     * 顺序，比如 {1,3,423,-534,346,214,543,64,24}
     *           {1,3} {423,-534} {346,214} {543,64} {24}
     *   内部排序 {1，3} {-534，423} {214，346} {64，543} {24}
     *   归并排   {-534，1，3，423} {64，214，346，543} {24}
     *   归并排   {-534，1，3，64，214，346，423，543} {24}
     *   归并排   {-543 ，1，3，24，64，214，346，423，543}
     *   最后完成排序
     *   时间复杂度为O(nlgn)*/
     public void mergeSort(int [] a , int left, int right){
         if(left>=right) return;        //如果左边大于右边则不返回任何结果
         int center=(left+right)/2;
         mergeSort(a,left,center);
         mergeSort(a,center+1,right); //递归分解
         merge(a,left,center,right);

     }

    private void merge(int[] a, int left, int center, int right) {      //将两个有序数组组合
         int [] temp=new int[a.length];
         int rLeft=center+1;
         int tIndex=left;
         int cIndex=left;
         while (left<=center&&rLeft<=right){
             if(a[left]>a[rLeft]){
                 temp[tIndex++]=a[rLeft++];
             }else {
                 temp[tIndex++]=a[left++];
             }
         }
         while (left<=center){
             temp[tIndex++]=a[left++];
         }
         while (rLeft<=right){
             temp[tIndex++]=a[rLeft++];
         }
         while (cIndex<=right){
             a[cIndex]=temp[cIndex];
             cIndex++;
         }
    }
    /**快速排序
     * 思路：将长度n的a数组的第一个元素定为轴心点，依次遍历数组，将比轴心点小的数放到轴心点左侧；
     * 再将轴心点左侧和右侧的数组拆分成两个，继续做相同操作，以此类推，最后数组就能排好。
     * 算法复杂度：nlgn*/
    public void quickSort(int [] a,int left ,int right){
        if(left>=right) return;
        int cenPoint=a[left]; //设值轴心点
        int temp;
        int lIndex=left;
        for (int i = left+1; i <right ; i++) {
            if(a[i]<cenPoint){
                lIndex++;
                temp=a[i];
                a[i]=a[lIndex];
                a[lIndex]=temp;
            }
        }
        temp=a[lIndex];
        a[lIndex]=a[left];
        a[left]=temp;
        quickSort(a,left,lIndex-1);
        quickSort(a,lIndex+1,right);
    }
}

