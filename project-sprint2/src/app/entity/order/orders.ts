export interface Orders {
  orderId: number;
  flagDelete?: boolean;
  orderDate?: string;
  accountId: number;
  paymentStatus?: boolean;
  address?: string;
  phoneNumber?: string;
  note?: string;
}
