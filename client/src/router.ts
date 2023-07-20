import { getAuth, onAuthStateChanged } from 'firebase/auth';
import { createRouter, createWebHistory } from 'vue-router';

const HomePage = () => import('./pages/HomePage.vue');
const RegisterPage = () => import('./pages/RegisterPage.vue')
const ProfilePage = () => import('./pages/ProfilePage.vue');
const InsurancePage = () => import('./pages/InsurancePage.vue');
const CarAddPage = () => import('./pages/CarAddPage.vue');
const ContractAddPage = () => import('./pages/ContractAddPage.vue');
const PlanPage = () => import('./pages/PlanPage.vue');
const CarPage = () => import('./pages/CarPage.vue');
const ContractPage = () => import('./pages/ContractPage.vue');
const ConsultantPage = () => import('./pages/ConsultantPage.vue');
const CompanyPage = () => import('./pages/CompanyPage.vue');
const NotFoundPage = () => import('./pages/NotFoundPage.vue');

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { 
      path: '/', 
      component: HomePage 
    },
    {
      path: '/register',
      component: RegisterPage,
    },
    {
      path: '/profile',
      component: ProfilePage,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/insurance',
      component: InsurancePage,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/newcar',
      component: CarAddPage,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/newcontract',
      component: ContractAddPage,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/company',
      name: 'companyPage',
      component: CompanyPage,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/plan',
      component: PlanPage,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/car',
      component: CarPage,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/contract',
      name: 'contractPage',
      component: ContractPage,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/consultant',
      component: ConsultantPage,
      meta: {
        requiresAuth: true,
      }
    },
    { 
      path: '/:notFound(.*)', 
      component: NotFoundPage 
    },
  ],
});

const getCurrentUser = () => {
  return new Promise((resolve, reject) => {
    const removeListener = onAuthStateChanged(
      getAuth(),
      (user) => {
        removeListener();
        resolve(user);
      },
      reject
    )
  })
}

router.beforeEach(async (to, from, next) => {
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    if (await getCurrentUser()) {
      next();
    } else {
      alert("You don't have an access!");
      next("/");
    }
  } else {
    next();
  }
})
 
export default router;