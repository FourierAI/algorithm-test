
/**
 * 问题：现在有长度0-N的序列，序列元素为任意实数，求最大的子序列和*/

public class MaxSubSeqSum {
    public static void main(String[] args){
        int test[]={10130,-43221,1233112,43312123,-31213123,212213,-412342};
        MaxSubSeqSum maxSubSeqSum=new MaxSubSeqSum();
        System.out.println(maxSubSeqSum.methodOne(test));
        System.out.println(maxSubSeqSum.methodTwo(test));
        System.out.println(maxSubSeqSum.methodThree(test));
        System.out.println(maxSubSeqSum.methodFour(test));

    }
    /**解决方法一：3层暴力循环遍历
     * 算法复杂度显示是三层循环相乘也就是N^3次。*/
    public int methodOne(int a[]){
        int maxSubSeqSum=0;
        int first=0;
        int last=0;
        for (int i = 0; i <a.length ; i++) {        //第一层循环i只向序列的起始位置
            for (int j = i; j <a.length ; j++) {    //第二层循环j只向序列的终止位置
                int thisSubSeqSum=0;                //注意要在这里定义thisSubSeqSum不然值会在循环中不断累加
                for (int k = i; k <=j ; k++) {       //第三层循环累加从i到j的SUM
                    thisSubSeqSum=thisSubSeqSum+a[k];
                }
                if(thisSubSeqSum>=maxSubSeqSum)
                {
                    maxSubSeqSum=thisSubSeqSum;
                    first=i;
                    last=j;
                }
            }
        }
        System.out.println(first+"---"+last);
        return maxSubSeqSum;
    }
    /**
     * 解决方案二：我们从一基础上不难发现第三层循环的累加过程有重复加法在里面，而且这种计算浪费十分巨大，我们
     * 试着把第三层循环去掉看看。
     * 注意：我们总共改了2处地方，第一处去掉了for(k...)循环，第二处将thisSubSeqSum移到了第一层循环内第二层循环外，仔细体会
     * 此方法复杂度为N^2*/
    public int methodTwo(int a[]){
        int maxSubSeqSum=0;
        int first=0;
        int last=0;
        for (int i = 0; i <a.length ; i++) {        //第一层循环i只向序列的起始位置
            int thisSubSeqSum=0;                //注意要在这里定义thisSubSeqSum不然值会在循环中不断累加
            for (int j = i; j <a.length ; j++) {    //第二层循环j只向序列的终止位置
                thisSubSeqSum=thisSubSeqSum+a[j];
                if(thisSubSeqSum>=maxSubSeqSum)
                {
                    maxSubSeqSum=thisSubSeqSum;
                    first=i;
                    last=j;
                }
            }
        }
        System.out.println(first+"---"+last);
        return maxSubSeqSum;
    }
    /**解决方案三：我们换一种思路，正如浙江大学陈越老师所言，当我们的算法复杂度达到N^2的时候，我们的方案已经很烂了，我们
     * 就应该试着将算法提高到nlogn这种阶段
     * 这里我们采用分治策略。我们将数组分为左右两半比较，最大子序列和出现的位置有三种情况：
     * 1.左半边
     * 2.右半边
     * 3.跨越左半边最后一个元素与右半边最前面一个元素的子串
     * 所以我们先将数组递归调用，将其不断的分成最小单位；即一个元素，
     * 这里我们采用一个小技巧，因为数组left是小于等于right的，当left==right的时候，就是一个元素的时候，感兴趣的伙伴可以
     * 试一试。这样我们已经完成了分的操作，现在就是到了合的过程，
     * 显然我们要比较左半边，右半边以及跨越‘两边的部分’然后作为函数返回值，这里需要计算跨越‘两边部分’的最大子序列，我们同样需要分为两路，
     * 这里不是什么思想，而是一种技巧，我们在下面的程序中会看到这种技巧使用（所以想了想算法实际就是人类解决问题的常用技巧的学习，属于经验学范畴，
     * 如果科学也是经验学衍生的话。。。哈哈再扯下去就快到唯心主义还是唯物主义的哲学了）
     * 此方法复杂度为nlogn*/
    public int methodThree(int a[]){
        return methodThreeIdr(a,0,a.length-1);
    }
    public int methodThreeIdr(int a[] ,int left ,int right){
        /**确立基准情况base case，防止死循环*/
        if(left==right) {
            if (a[left] > 0) {
                return a[left];
            } else {
                return 0;           //其实这里return 多少不重要，可省略。只是象征性的将负数归零讨论。大家可以试试。
            }
        }
        int center=(left+right)/2;
        int maxLeftSum=methodThreeIdr(a,left,center);
        int maxRightSum=methodThreeIdr(a,center+1,right);       //递归分解

        int leftCurrentSum=0,maxLeftBorderSum=0;
        for (int i = center; i >=left ; i--) {
            leftCurrentSum=leftCurrentSum+a[i];
            if(leftCurrentSum>maxLeftBorderSum)
                maxLeftBorderSum=leftCurrentSum;
        }

        int rightCurrentSum=0,maxRightBorderSum=0;
        for (int i = center+1; i <=right ; i++) {
            rightCurrentSum=rightCurrentSum+a[i];
            if(rightCurrentSum>maxRightBorderSum)
                maxRightBorderSum=rightCurrentSum;
        }
        return max3(maxLeftSum,maxRightSum,maxLeftBorderSum+maxRightBorderSum);
    }

    private int max3(int maxLeftSum, int maxRightSum, int i) {
        if(maxLeftSum>maxRightSum){
            if(maxLeftSum>i) return maxLeftSum;
            else return i;
        }else {
            if(maxRightSum>i) return maxRightSum;
            else return i;
        }
    }
    /**
     * 解决方案四：到这里，头已经分析的够大了，别急还没到休息的时候，我们还有更屌的算法，别不想看，这个理解起来没有刚才那个复杂，
     * 所以再坚持下，这里我们用到一个算法名字叫在线算法，用到了一种思想，比如有个0-N序列，前K项和为负数，我们显然再往后加，都
     * 没有从k+1项开始来的大。我们可以在前k项和为负数的时候清零，这样就保证了正数条件，继续往下加的话，肯定是增长的。我们来试试。
     * 算法复杂度为N*/
    public int methodFour(int a[]){
        int currentSum=0;
        int maxSum=0;
        for (int i = 0; i < a.length; i++) {
            currentSum=currentSum+a[i];
            if(currentSum<0) currentSum=0;
            if(currentSum>maxSum) maxSum=currentSum;
        }
        return maxSum;
    }

}
