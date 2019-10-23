public class Matrix_Chain_Mul {
static int Rec_matrix_chain(int dim[], int i, int j)
{
if (i == j)
return 0;
int min = Integer.MAX_VALUE;
for (int k=i; k<j; k++)
{
int q = Rec_matrix_chain(dim, i, k) +
Rec_matrix_chain(dim, k+1, j) +
dim[i-1]*dim[k]*dim[j];
if (q < min)
min = q;

}
return min;
}
public static void main(String args[])
{
int dim[] = new int[] {10, 20, 30, 40, 50};
int n = Rec_matrix_chain(dim, 1, dim.length-1);
System.out.println(n);
}
}
