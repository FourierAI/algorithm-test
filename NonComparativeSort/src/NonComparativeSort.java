/**
 * 冒泡排序，选择排序，归并排序，快速排序都是基于比较模型的排序策略。比较排序的时间复杂度最快是nlgn的复杂度。
 * 要是想做到线性时间的复杂度，必须用空间换时间，比如计数排序于基数排序。复杂度都是O(n)*/
public class NonComparativeSort {
    public static void main(String[] args){
        int [] test={1,43,2,4,52,532,312,42};
        NonComparativeSort nonComparativeSort=new NonComparativeSort();
        nonComparativeSort.baseNumSort(test,3);
        for (int i: test
             ) {
            System.out.println(i+"  ");
        }
    }
    /**
     * 计数排序
     * 思路：空间换时间，如果待排序的数组知道大体范围（m,n），则可以预先预留m-n+1的空间
     * 读取待排序的数组的数，并在临时开辟的空间里面统计次数，返回的时候，再逆向把数据送回原数组。
     * 复杂度为O(n)*/
    public void countSort(int [] a,int minimum,int maximum){
        int [] temp =new int[maximum-minimum+1];
        for (int i = 0; i <a.length ; i++) {
            temp[a[i]-minimum]++;
        }
        for (int i = 0,count=0; i <temp.length ; i++) {
            while (temp[i]!=0){
                a[count]=i+minimum;
                count++;
                temp[i]--;
            }
        }
    }
    /**
     * 基数排序
     * 思路：空间换时间，将个位计数排序，然后将十位计数排序，依次类推。
     * 时间复杂度显然是k*n，k为数组最大数的位数，当我们认为n无穷大时，k可以忽略。
     * 复杂度为O(n)*/
    public void baseNumSort(int [] a,int places){

        int k = 0;
        int n = 1;
        int m = 1;                                  //控制键值排序依据在哪一位
        int[][]temp = new int[10][a.length];        //数组的第一维表示可能的余数0-9
        int[]order = new int[10];                   //数组orderp[i]用来表示该位是i的数的个数
        while(m <= places)
        {
            for(int i = 0; i < a.length; i++)
            {
                int lsd = ((a[i] / n) % 10);
                temp[lsd][order[lsd]] = a[i];
                order[lsd]++;
            }
            for(int i = 0; i < 10; i++)
            {
                if(order[i] != 0)
                    for(int j = 0; j < order[i]; j++)
                    {
                        a[k] = temp[i][j];
                        k++;
                    }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
            m++;
        }
    }
}
